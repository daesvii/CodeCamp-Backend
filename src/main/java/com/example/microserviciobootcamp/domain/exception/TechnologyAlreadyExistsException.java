package com.example.microserviciobootcamp.domain.exception;

public class TechnologyAlreadyExistsException extends RuntimeException{
    public TechnologyAlreadyExistsException (String message) {super(message);}
}
