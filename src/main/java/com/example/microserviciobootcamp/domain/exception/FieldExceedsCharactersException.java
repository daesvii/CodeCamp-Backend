package com.example.microserviciobootcamp.domain.exception;

public class FieldExceedsCharactersException extends RuntimeException {
    public FieldExceedsCharactersException (String message) {super(message);}
}
