package com.wayne.springboot3apitemplate.configuration;

import com.wayne.springboot3apitemplate.model.ExceptEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * 定义web全局的异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExceptEntity> exceptionHandler(RuntimeException e) {

        return ResponseEntity.unprocessableEntity()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptEntity(e.getMessage(), new Date(), 422));
    }



}