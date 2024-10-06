package com.example.microserviciobootcamp.adapters.driving.http.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Valid
public class AddBootcampRequest {

    @NotNull(message = "The name cannot be null")
    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    private final String name;

    @NotNull(message = "The description cannot be null")
    @NotEmpty(message = "The description cannot be empty")
    @Size(min = 1, max = 90, message = "The description must be between 1 and 90 characters")
    private final String description;

    @NotNull(message = "The abilityIds cannot be null")
    @NotEmpty(message = "The abilityIds cannot be empty")
    private final List<Long> abilityIds;
}
