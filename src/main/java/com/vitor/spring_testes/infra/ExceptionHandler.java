package com.vitor.spring_testes.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

    
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> IlegalArgumentException(IllegalArgumentException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }

}
