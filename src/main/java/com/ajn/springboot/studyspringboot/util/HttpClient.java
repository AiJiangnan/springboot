package com.ajn.springboot.studyspringboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 艾江南
 */
public final class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    private final static RestTemplate REST_TEMPLATE;

    static {
        REST_TEMPLATE = new RestTemplateBuilder()
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .uriTemplateHandler(new SimpleUriTemplateHandler())
                .setReadTimeout(5000)
                .setConnectTimeout(15000)
                .build();
    }

    public static <T> T post(String url, Object request, Class<T> responseType) {
        return REST_TEMPLATE.postForObject(url, request, responseType);
    }

    /**
     * 用于创建URI参数
     */
    private static class SimpleUriTemplateHandler implements UriTemplateHandler {

        @Override
        public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
            UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(uriTemplate);
            for (Map.Entry<String, ?> variable : uriVariables.entrySet()) {
                url.queryParam(variable.getKey(), variable.getValue());
            }
            URI uri = url.build().encode().toUri();
            logger.debug("请求Rest接口路径: {}", uri);
            return uri;
        }

        @Override
        public URI expand(String uriTemplate, Object... uriVariables) {
            // URI参数不建议使用传对象，使用Map
            return URI.create(uriTemplate);
        }
    }
}
