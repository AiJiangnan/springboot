package com.ajn.springboot.studyspringboot.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.ajn.springboot.studyspringboot.util.HttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoggerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    protected void append(ILoggingEvent eventObject) {
        String message = "#### SpringBoot项目警报\n" +
                "- 报警级别: " + eventObject.getLevel() + "\n" +
                "- 报警时间: " + format.format(new Date(eventObject.getTimeStamp())) + "\n" +
                "- 报警位置: " + eventObject.getLoggerName() + "\n" +
                "- 报警信息: " + eventObject.getFormattedMessage();
        Map<String, Object> request = new HashMap<>(3);
        Map<String, Object> content = new HashMap<>(3);

        content.put("title", "SpringBoot项目警报");
        content.put("text", message);
        content.put("hideAvatar", 1);
        request.put("msgtype", "actionCard");
        request.put("actionCard", content);
        request.put("btns", null);

        String dingdingUrl = "https://oapi.dingtalk.com/robot/send?access_token=000917204f79da53814423345f26bdb910003aa80df0d3ec0d3f4f4d7454210e";
        HttpClient.post(dingdingUrl, request, Object.class);
    }
}
