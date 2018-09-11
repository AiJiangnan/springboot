package com.ajn.springboot.studyspringboot.util;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 艾江南
 */
public final class HttpClient {

    private final static RestTemplate REST_TEMPLATE;

    static {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        REST_TEMPLATE = new RestTemplate(factory);
    }

    public static <T> T post(String url, Object request, Class<T> responseType) {
        return REST_TEMPLATE.postForObject(url, request, responseType);
    }
}
