package com.roboticks.robobob.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.script.ScriptException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScriptException.class)
    public ResponseEntity<String> handleScriptException(ScriptException ex) {
        return ResponseEntity.badRequest().body("Invalid arithmetic expression.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}
