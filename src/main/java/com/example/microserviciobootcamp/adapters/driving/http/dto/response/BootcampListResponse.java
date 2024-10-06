package com.example.microserviciobootcamp.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BootcampListResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<AbilityDetails> abilities;


    @AllArgsConstructor
    @Getter
    public static class AbilityDetails {
        private final Long id;
        private final String name;
        private final List<TechnologyDetails> technologies;
    }

    @AllArgsConstructor
    @Getter
    public static class TechnologyDetails {
        private final Long id;
        private final String name;
    }
}
