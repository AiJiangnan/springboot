package com.ajn.springboot.studyspringboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author 艾江南
 * @date 2018/9/5
 */
@Getter
@Setter
public class RestResponse<T> {

    private int code;
    private String msg;
    private T data;

    public RestResponse(HttpStatus status) {
        this(status, null);
    }

    public RestResponse(HttpStatus status, T data) {
        this(status.value(), status.getReasonPhrase(), data);
    }

    public RestResponse(int code, String msg) {
        this(code, msg, null);
    }

    public RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResponse serverError() {
        return new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static RestResponse serverError(String msg) {
        return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static RestResponse badRequest() {
        return new RestResponse(HttpStatus.BAD_REQUEST);
    }

    public static RestResponse badRequest(String msg) {
        return new RestResponse<>(HttpStatus.BAD_REQUEST.value(), msg);
    }

    public static RestResponse ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<>(HttpStatus.OK, data);
    }
}
