package com.github.line.schedulereadonlyapi.exception;

public class UsernameTakenException extends RuntimeException{
    public UsernameTakenException(String s) {
        super(s);
    }
}
