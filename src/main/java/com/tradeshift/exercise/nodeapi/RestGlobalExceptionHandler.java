package com.tradeshift.exercise.nodeapi;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Conflict Error.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { TradeShiftNodeNotFoundException.class })
    protected ResponseEntity<Object> handleTradeShiftNodeNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Resource not Found.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { CircularTreeDependencyException.class })
    protected ResponseEntity<Object> handleCircularTreeDependencyException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Circular Tree Dependency error.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { CannotSetParentOfRootException.class })
    protected ResponseEntity<Object> handleCannotSetParentToRootException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Root used to set parent.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<Object> handleFallbackConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Internal Error.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}