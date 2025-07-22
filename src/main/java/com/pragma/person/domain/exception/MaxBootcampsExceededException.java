package com.pragma.person.domain.exception;

public class MaxBootcampsExceededException extends RuntimeException {

    public MaxBootcampsExceededException(String message) {
        super(message);
    }

}