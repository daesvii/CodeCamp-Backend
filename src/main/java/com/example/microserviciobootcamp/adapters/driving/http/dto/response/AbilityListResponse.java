package com.example.microserviciobootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AbilityListResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<TechnologyDetails> technologies;

    @AllArgsConstructor
    @Getter
    public static class TechnologyDetails {
        private final Long id;
        private final String name;
    }
}
