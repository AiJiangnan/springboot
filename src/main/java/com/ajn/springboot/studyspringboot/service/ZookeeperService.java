package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.CuratorExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 艾江南
 * @date 2018/9/13
 */
@Service
public class ZookeeperService {

    final private Logger logger = LoggerFactory.getLogger(ZookeeperService.class);

    @Autowired
    private CuratorExecutor curatorExecutor;

    public String get() {
        String s = curatorExecutor.get("/abc");
        logger.debug("get: {}", s);
        return s;
    }

    public void watch() {
        curatorExecutor.watch("/abc", (client, event) -> {
            logger.debug("事件类型: {}", event.getType());
            if (event.getData() != null) {
                logger.debug("节点数据: {} = {}", event.getData().getPath(), new String(event.getData().getData()));
            }
        });
    }

}
