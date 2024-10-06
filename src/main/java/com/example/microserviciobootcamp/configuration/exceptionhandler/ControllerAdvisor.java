package com.example.microserviciobootcamp.configuration.exceptionhandler;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.InvalidOrderByException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.configuration.Constants;
import com.example.microserviciobootcamp.domain.exception.*;
import com.example.microserviciobootcamp.domain.util.DomainConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(MissingDataException.class)
    public ResponseEntity<ExceptionResponse> handleMissingDataException(MissingDataException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.MISSING_DATA_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(errors.toString()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(DomainConstants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(FieldExceedsCharactersException.class)
    public ResponseEntity<ExceptionResponse> handleFieldExceedsCharactersException(FieldExceedsCharactersException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.FIELD_EXCEEDS_CHARACTERS_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException(ElementNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(DataRepeatsItselfException.class)
    public ResponseEntity<ExceptionResponse> handleDataRepeatsItselfException(DataRepeatsItselfException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.DATA_REPEATS_ITSELF_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(MinimumDataFieldMissingException.class)
    public ResponseEntity<ExceptionResponse> handleMinimumDataFieldMissingException(MinimumDataFieldMissingException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.MINIMUM_DATA_FIELD_MISSING_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
    @ExceptionHandler(EndDateBeforeStartDateException.class)
    public ResponseEntity<ExceptionResponse> handleEndDateBeforeStartDateException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
            DomainConstants.END_DATE_BEFORE_START_DATE_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
        ));
    }

    @ExceptionHandler(InvalidOrderByException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidOrderByException(InvalidOrderByException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.INVALID_ORDERBY_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
