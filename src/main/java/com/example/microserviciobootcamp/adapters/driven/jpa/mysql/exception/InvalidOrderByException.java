package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception;

public class InvalidOrderByException extends RuntimeException{
    public InvalidOrderByException(String message) {
        super(message);
    }
}
