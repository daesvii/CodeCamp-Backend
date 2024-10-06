package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.TechnologyHandlerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyRestControllerAdapterTest {
    @Mock
    TechnologyHandlerImpl technologyHandler;

    @InjectMocks
    TechnologyRestControllerAdapter technologyRestControllerAdapter;

    @Test
    void addTechnology() {
        // Arrange
        AddTechnologyRequest technologyRequest = new AddTechnologyRequest("Technology", "Description");

        TechnologyResponse technologyResponse = new TechnologyResponse(1L, "Technology", "Description");

        when(technologyHandler.saveTechnology(technologyRequest)).thenReturn(technologyResponse);

        // Act
        ResponseEntity<TechnologyResponse> responseEntity = technologyRestControllerAdapter.addTechnology(technologyRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(technologyResponse, responseEntity.getBody());
    }

    @Test
    void getAllTechnologies() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;

        List<TechnologyResponse> technologyResponses = Arrays.asList(
                new TechnologyResponse(1L, "Technology1", "Description1"),
                new TechnologyResponse(2L, "Technology2", "Description2")
        );

        when(technologyHandler.getAllTechnologies(page, size, ascending)).thenReturn(technologyResponses);

        // Act
        ResponseEntity<List<TechnologyResponse>> responseEntity = technologyRestControllerAdapter.getAllTechnologies(page, size, Optional.of(ascending));

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(technologyResponses, responseEntity.getBody());
    }

    @Test
    void getTechnology() {
        // Arrange
        String technologyName = "Technology";
        TechnologyResponse technologyResponse = new TechnologyResponse(1L, technologyName, "Description");

        when(technologyHandler.getTechnology(technologyName)).thenReturn(technologyResponse);

        // Act
        ResponseEntity<TechnologyResponse> responseEntity = technologyRestControllerAdapter.getTechnology(technologyName);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(technologyResponse, responseEntity.getBody());
    }
}