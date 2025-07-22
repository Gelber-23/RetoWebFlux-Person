package com.pragma.person.infraestructure.exceptionhandler;

import com.pragma.person.domain.exception.*;
import com.pragma.person.domain.util.ExceptionConstans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice

public class ControllerAdvisor {

    private static final String MESSAGE = ExceptionConstans.FIRST_PART_MESSAGE_EXCEPTION;
    private static final String STATUS = ExceptionConstans.STATUS_MESSAGE_EXCEPTION;

    @ExceptionHandler(BootcampNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBootcampNotFound(BootcampNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePersonNotFound(PersonNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }

    @ExceptionHandler(BootcampOverlapException.class)
    public ResponseEntity<Map<String, Object>> handleBootcampOverlapValidation(BootcampOverlapException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(DuplicateBootcampException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateBootcamp(DuplicateBootcampException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(InvalidPersonException.class)
    public ResponseEntity<Map<String, Object>> handlePersonValidation(InvalidPersonException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(InvalidRequestBootcampException.class)
    public ResponseEntity<Map<String, Object>> handleRequestBootcampValidation(InvalidRequestBootcampException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
    @ExceptionHandler(MaxBootcampsExceededException.class)
    public ResponseEntity<Map<String, Object>> handleBootcampExceedValidation(MaxBootcampsExceededException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(Map.of(
                        STATUS, status.value(),
                        MESSAGE, ex.getMessage()
                ));
    }
}
