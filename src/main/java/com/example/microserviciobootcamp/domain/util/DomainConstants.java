package com.example.microserviciobootcamp.domain.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field{
        NAME,
        DESCRIPTION,
        TECHNOLOGY_IDS,
        ABILITY_IDS,
        BOOTCAMP_ID
    }
    public static final int MAX_TECHNOLOGIES_SIZE = 20;
    public static final int MIN_TECHNOLOGIES_SIZE = 3;
    public static final int MAX_ABILITIES_SIZE = 4;
    public static final int MIN_ABILITIES_SIZE = 1;

    public static final String TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The technology you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String FIELD_EXCEEDS_CHARACTERS_EXCEPTION_MESSAGE = "Field %s exceeds the maximum limit";
    public static final String MISSING_DATA_EXCEPTION_MESSAGE = "Field %s cannot be null";
    public static final String DATA_REPEATS_ITSELF_EXCEPTION_MESSAGE = "There are %s that are repeated";
    public static final String MINIMUM_DATA_FIELD_MISSING_EXCEPTION_MESSAGE = "Field %s  does not meet the minimum required";
    public static final String END_DATE_BEFORE_START_DATE_EXCEPTION_MESSAGE = "The end date cannot be before the start date";
}
