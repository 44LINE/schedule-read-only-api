package com.github.line.schedulereadonlyapi.exception;

import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NonUniqueResultException;

@RestControllerAdvice
public class SignUpAdvice extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {UsernameTakenException.class, NonUniqueResultException.class})
    public Problem notFoundException(final Exception e) {
        return Problem.create().withDetail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = BadCredentialsException.class)
    public Problem badCredentials(final BadCredentialsException e) {
        return Problem.create().withDetail(e.getMessage());
    }


}
