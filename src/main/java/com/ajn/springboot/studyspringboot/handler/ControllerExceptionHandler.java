package com.ajn.springboot.studyspringboot.handler;

import com.ajn.springboot.studyspringboot.entity.RestResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author 艾江南
 * @date 2018/9/7
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public RestResponse validationExceptionHandler(Exception e) {
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exception = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violationSet = exception.getConstraintViolations();
            ConstraintViolation<?> violation = violationSet.iterator().next();
            return RestResponse.badRequest(violation.getMessage());
        }
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getBindingResult().getAllErrors();
            return RestResponse.badRequest(errors.get(0).getDefaultMessage());
        }
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = exception.getBindingResult().getAllErrors();
            return RestResponse.badRequest(errors.get(0).getDefaultMessage());
        }
        e.printStackTrace();
        return RestResponse.serverError(e.getMessage());
    }

}
