package com.mingming.commonservice.advice;

import com.mingming.commonservice.common.CommonException;
import com.mingming.commonservice.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(Exception ex){
        log.error("Unknown internal server error: "+ex.getMessage());
        log.error("Exception class: "+ex.getClass());
        log.error("Exception cause: "+ex.getCause());
        return new ResponseEntity(new ErrorMessage("9999", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleCommonException(CommonException e){
        log.error(String.format("Common error: %s %s %s", e.getCode(), e.getMessage(), e.getStatus()));
        return new ResponseEntity(new ErrorMessage(e.getCode(), e.getMessage(), e.getStatus()), e.getStatus());
    }
}
