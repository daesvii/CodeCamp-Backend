package com.example.microserviciobootcamp.domain.exception;

public class DataRepeatsItselfException extends RuntimeException{
    public DataRepeatsItselfException(String message) {super(message);}
}
