package com.example.microserviciobootcamp.domain.exception;

public class MinimumDataFieldMissingException extends RuntimeException{
    public MinimumDataFieldMissingException (String message) {super(message);}
}
