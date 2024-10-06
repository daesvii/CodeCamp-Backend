package com.example.microserviciobootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VersionBootcampResponse {
    private final Long id;
    private final int maxCapacity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long bootcampId;
}
