package com.klapsia.inpost.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> unexpectedErrorHandler(Exception exception) {
        log.error("unexpected error", exception);
        return ResponseEntity.status(
                HttpStatus.SERVICE_UNAVAILABLE.value()).body(new Error(HttpStatus.SERVICE_UNAVAILABLE.value(), exception.getMessage()));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<Error> badRequestException(ServerWebInputException exception) {
        log.info("bad request", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new Error(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> notFoundException(RuntimeException exception) {
        log.info("bad request", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new Error(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }
}
