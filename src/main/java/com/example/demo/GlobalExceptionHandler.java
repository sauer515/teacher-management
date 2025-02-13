package com.example.demo;

import com.example.demo.exception.RateValueException;
import com.example.demo.exception.TeacherAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<String> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RateValueException.class)
    public ResponseEntity<String> handleRateValueException(RateValueException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}