package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message) {
        super(message);
    }
}

