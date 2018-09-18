package com.ajn.springboot.studyspringboot.executor;

import com.ajn.springboot.studyspringboot.config.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 艾江南
 * @date 2018/9/13
 */
@Component
@Profile("prod")
public class CuratorExecutor {

    final private static Logger logger = LoggerFactory.getLogger(CuratorExecutor.class);

    @Autowired
    private ZookeeperProperties zookeeperProperties;

    private CuratorFramework client;
    private PathChildrenCache cache;

    @PostConstruct
    private void init() {
        logger.debug("Connect zookeeper: {}", zookeeperProperties.getHost());
        client = CuratorFrameworkFactory.newClient(zookeeperProperties.getHost(),
                new ExponentialBackoffRetry(1000, 3));
        client.start();
    }

    @PreDestroy
    public void destroy() {
        if (cache != null) {
            try {
                logger.debug("Close zookeeper cache successful");
                cache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (client != null) {
            logger.debug("Close zookeeper successful");
            client.close();
        }
    }

    public void create(String path, String data) {
        try {
            client.create().forPath(path, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String path) {
        try {
            return new String(client.getData().forPath(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(String path, String data) {
        try {
            client.setData().forPath(path, data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void watch(String path, PathChildrenCacheListener listener) {
        // new TreeCache()
        // new NodeCache()
        try {
            cache = new PathChildrenCache(client, path, true);
            cache.start();
            cache.getListenable().addListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
