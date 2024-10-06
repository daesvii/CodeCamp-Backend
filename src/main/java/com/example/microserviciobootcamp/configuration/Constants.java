package com.example.microserviciobootcamp.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final int MAX_DESCRIPTION_LENGTH = 90;
    public static final int MAX_NAME_LENGTH = 50;

    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "Some field of %s does not exist.";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String INVALID_ORDERBY_EXCEPTION_MESSAGE = "The orderby field %s is not valid. Please, try again.";
}
