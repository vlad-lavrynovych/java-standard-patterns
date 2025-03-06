package com.demo.patterns.spring_cloud_gw.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class ReactiveErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ProblemDetail>> handleResponseStatusException(ResponseStatusException ex, ServerWebExchange exchange) {
        log.error("ResponseStatusException: {}", ex.toString());
        return Mono.just(new ResponseEntity<>(ex.getBody(), HttpStatus.resolve(ex.getBody().getStatus())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ProblemDetail>> handleException(Exception ex, ServerWebExchange exchange) {
        log.error("Exception occurred", ex);
        return buildProblemDetail(ex, exchange, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Mono<ResponseEntity<ProblemDetail>> buildProblemDetail(Exception ex, ServerWebExchange exchange, HttpStatus httpStatus) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("path", exchange.getRequest().getPath().toString());
        problemDetail.setDetail(ex.getMessage());

        return Mono.just(ResponseEntity.status(httpStatus).body(problemDetail));
    }
}
