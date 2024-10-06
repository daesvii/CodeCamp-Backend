package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddAbilityRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IAbilityRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IAbilityResponseMapper;
import com.example.microserviciobootcamp.domain.api.IAbilityServicePort;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbilityHandlerImplTest {

    @Mock
    IAbilityRequestMapper abilityRequestMapper;

    @Mock
    IAbilityResponseMapper abilityResponseMapper;

    @Mock
    IAbilityServicePort abilityServicePort;

    @InjectMocks
    AbilityHandlerImpl abilityHandler;

    @Test
    void saveAbility() {
        // Arrange
        AddAbilityRequest request = new AddAbilityRequest("Ability", "Description", List.of(1L, 2L, 3L));

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Technology 1", "Description"));
        technologies.add(new Technology(2L, "Technology 2", "Description"));
        technologies.add(new Technology(3L, "Technology 3", "Description"));

        Ability ability = new Ability(1L, "Ability", "Description", technologies);
        AbilityResponse expectedResponse = new AbilityResponse(1L, "Ability", "Description", List.of(1L, 2L, 3L));

        when(abilityRequestMapper.addRequestToAbility(request)).thenReturn(ability);
        when(abilityServicePort.saveAbility(ability)).thenReturn(ability);
        when(abilityResponseMapper.toAbilityResponse(ability)).thenReturn(expectedResponse);

        // Act
        AbilityResponse actualResponse = abilityHandler.saveAbility(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllAbilities() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;
        String orderBy = "name";

        List<Ability> abilities = Arrays.asList(
                new Ability(1L, "Ability1", "Description1", new ArrayList<>()),
                new Ability(2L, "Ability2", "Description2", new ArrayList<>())
        );

        List<AbilityListResponse> expectedResponse = Arrays.asList(
                new AbilityListResponse(1L, "Ability1", "Description1", new ArrayList<>()),
                new AbilityListResponse(2L, "Ability2", "Description2", new ArrayList<>())
        );

        when(abilityServicePort.getAllAbilities(page, size, ascending, orderBy)).thenReturn(abilities);
        when(abilityResponseMapper.toAbilityResponseList(abilities)).thenReturn(expectedResponse);

        // Act
        List<AbilityListResponse> actualResponse = abilityHandler.getAllAbilities(page, size, ascending, orderBy);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}