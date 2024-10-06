package com.example.microserviciobootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VersionBootcampListResponse {
    private final Long id;
    private final int maxCapacity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BootcampDetails bootcamp;

    @AllArgsConstructor
    @Getter
    public static class BootcampDetails {
        private final Long id;
        private final String name;
    }
}
