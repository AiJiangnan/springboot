package com.ajn.springboot.studyspringboot;

import com.ajn.springboot.studyspringboot.config.ZookeeperConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author 艾江南
 * @date 2018/9/13
 */
@Component
public class CuratorExecutor {

    final private static Logger logger = LoggerFactory.getLogger(CuratorExecutor.class);

    @Autowired
    private ZookeeperConfig zookeeperConfig;

    private CuratorFramework client;
    private PathChildrenCache cache;

    @PostConstruct
    private void init() {
        logger.debug("Connect zookeeper: {}", zookeeperConfig.getHost());
        client = CuratorFrameworkFactory.newClient(zookeeperConfig.getHost(),
                new ExponentialBackoffRetry(1000, 3));
        client.start();
    }

    @PreDestroy
    public void destroy() {
        if (client != null) {
            logger.debug("Close zookeeper successful");
            client.close();
        }
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void create(String path, String data) throws Exception {
        client.create().forPath(path, data.getBytes());
    }

    public String get(String path) throws Exception {
        return new String(client.getData().forPath(path));
    }

    public void set(String path, String data) throws Exception {
        client.setData().forPath(path, data.getBytes());
    }

    public void delete(String path) throws Exception {
        client.delete().forPath(path);
    }

    public void watch(String path, PathChildrenCacheListener listener) throws Exception {
        // new TreeCache()
        // new NodeCache()
        cache = new PathChildrenCache(client, path, true);
        cache.start();
        cache.getListenable().addListener(listener);
    }
}
