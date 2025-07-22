package com.pragma.person.domain.exception;

public class PersonNotFoundException  extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }

}