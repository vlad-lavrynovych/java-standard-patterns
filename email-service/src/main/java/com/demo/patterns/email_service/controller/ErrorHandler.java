package com.demo.patterns.email_service.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error("ConstraintViolationException occurred", ex);
        return new ResponseEntity<>(buildProblemDetail(ex, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex, WebRequest request) {
        log.error("Exception occurred", ex);
        return new ResponseEntity<>(buildProblemDetail(ex, request, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ProblemDetail buildProblemDetail(Exception ex, WebRequest request, HttpStatus httpStatus) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("path", request.getDescription(false));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
}
