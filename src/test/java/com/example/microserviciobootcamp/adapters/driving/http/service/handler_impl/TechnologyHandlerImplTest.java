package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.example.microserviciobootcamp.domain.api.ITechnologyServicePort;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyHandlerImplTest {

    @Mock
    ITechnologyRequestMapper technologyRequestMapper;

    @Mock
    ITechnologyResponseMapper technologyResponseMapper;

    @Mock
    ITechnologyServicePort technologyServicePort;

    @InjectMocks
    TechnologyHandlerImpl technologyHandler;

    @Test
    void saveTechnology() {
        // Arrange
        AddTechnologyRequest request = new AddTechnologyRequest("Technology", "Description");
        Technology technology = new Technology(1L, "Technology", "Description");
        TechnologyResponse expectedResponse = new TechnologyResponse(1L, "Technology", "Description");

        when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(technology);
        when(technologyServicePort.saveTechnology(technology)).thenReturn(technology);
        when(technologyResponseMapper.toTechnologyResponse(technology)).thenReturn(expectedResponse);

        // Act
        TechnologyResponse actualResponse = technologyHandler.saveTechnology(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllTechnologies() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;

        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Technology 1", "Description"),
                new Technology(2L, "Technology 2", "Description")
        );

        List<TechnologyResponse> expectedResponse = Arrays.asList(
                new TechnologyResponse(1L, "Technology 1", "Description"),
                new TechnologyResponse(2L, "Technology 2", "Description")
        );

        when(technologyServicePort.getAllTechnologies(page, size, ascending)).thenReturn(technologies);
        when(technologyResponseMapper.toTechnologyResponseList(technologies)).thenReturn(expectedResponse);

        // Act
        List<TechnologyResponse> actualResponse = technologyHandler.getAllTechnologies(page, size, ascending);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getTechnology() {
        // Arrange
        String technologyName = "Technology";
        Technology technology = new Technology(1L, "Technology", "Description");
        TechnologyResponse expectedResponse = new TechnologyResponse(1L, "Technology", "Description");

        when(technologyServicePort.getTechnology(technologyName)).thenReturn(technology);
        when(technologyResponseMapper.toTechnologyResponse(technology)).thenReturn(expectedResponse);

        // Act
        TechnologyResponse actualResponse = technologyHandler.getTechnology(technologyName);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}