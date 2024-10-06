package com.example.microserviciobootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AbilityResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Long> technologyIds;
}
