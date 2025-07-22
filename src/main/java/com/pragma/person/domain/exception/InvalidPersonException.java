package com.pragma.person.domain.exception;

public class InvalidPersonException extends RuntimeException {

    public InvalidPersonException(String message) {
        super(message);
    }

}