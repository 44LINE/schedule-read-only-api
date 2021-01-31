package com.github.line.schedulereadonlyapi.exception;

public class ClassObjectNotFoundException extends RuntimeException {
    private final Long id;

    public ClassObjectNotFoundException(final Long id) {
        super("Class object could not be found with id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
