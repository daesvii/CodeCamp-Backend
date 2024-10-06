package com.example.microserviciobootcamp.adapters.driving.http.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Valid
public class AddVersionBootcampRequest {

    @NotNull(message = "The max capacity cannot be null")
    private final int maxCapacity;

    @NotNull(message = "The start date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate startDate;

    @NotNull(message = "The end date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate endDate;

    @NotNull(message = "The bootcampId cannot be null")
    private final Long bootcampId;
}
