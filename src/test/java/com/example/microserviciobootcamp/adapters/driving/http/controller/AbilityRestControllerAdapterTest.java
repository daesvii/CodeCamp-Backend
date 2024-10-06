package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddAbilityRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.AbilityHandlerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbilityRestControllerAdapterTest {

    @Mock
    private AbilityHandlerImpl abilityHandler;

    @InjectMocks
    private AbilityRestControllerAdapter abilityRestControllerAdapter;

    @Test
    void addAbility() {
        // Arrange
        AddAbilityRequest abilityRequest = new AddAbilityRequest("AbilityName", "Description", new ArrayList<>());
        AbilityResponse abilityResponse = new AbilityResponse(1L, "AbilityName", "Description", new ArrayList<>());

        when(abilityHandler.saveAbility(abilityRequest)).thenReturn(abilityResponse);

        // Act
        ResponseEntity<AbilityResponse> responseEntity = abilityRestControllerAdapter.addAbility(abilityRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(abilityResponse, responseEntity.getBody());
    }

    @Test
    void getAllAbilities() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;
        String orderBy = "name";

        List<AbilityListResponse> abilityListResponses = Arrays.asList(
                new AbilityListResponse(1L, "Ability1", "Description1", new ArrayList<>()),
                new AbilityListResponse(2L, "Ability2", "Description2", new ArrayList<>())
        );

        when(abilityHandler.getAllAbilities(page, size, ascending, orderBy)).thenReturn(abilityListResponses);

        // Act
        ResponseEntity<List<AbilityListResponse>> responseEntity = abilityRestControllerAdapter.getAllAbilities(page, size, Optional.of(ascending), Optional.of(orderBy));

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(abilityListResponses, responseEntity.getBody());
    }
}