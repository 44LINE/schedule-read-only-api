package com.github.line.schedulereadonlyapi.exception;

import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ClassObjectControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ClassObjectNotFoundException.class)
    public Problem notFoundException(final ClassObjectNotFoundException e) {
        return Problem.create().withDetail(e.getMessage());
    }
}
