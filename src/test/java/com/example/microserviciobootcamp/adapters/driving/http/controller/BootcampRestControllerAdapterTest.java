package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.BootcampHandlerImpl;
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
class BootcampRestControllerAdapterTest {

    @Mock
    BootcampHandlerImpl bootcampHandler;

    @InjectMocks
    BootcampRestControllerAdapter bootcampRestControllerAdapter;

    @Test
    void addBootcamp() {
        // Arrange
        AddBootcampRequest bootcampRequest = new AddBootcampRequest("BootcampName", "Description", new ArrayList<>());
        BootcampResponse bootcampResponse = new BootcampResponse(1L, "BootcampName", "Description", new ArrayList<>());

        when(bootcampHandler.saveBootcamp(bootcampRequest)).thenReturn(bootcampResponse);

        // Act
        ResponseEntity<BootcampResponse> responseEntity = bootcampRestControllerAdapter.addBootcamp(bootcampRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(bootcampResponse, responseEntity.getBody());
    }

    @Test
    void getAllBootcamps() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;
        String orderBy = "name";

        List<BootcampListResponse> bootcampListResponses = Arrays.asList(
                new BootcampListResponse(1L, "Bootcamp1", "Description1", new ArrayList<>()),
                new BootcampListResponse(2L, "Bootcamp2", "Description2", new ArrayList<>())
        );

        when(bootcampHandler.getAllBootcamps(page, size, ascending, orderBy)).thenReturn(bootcampListResponses);

        // Act
        ResponseEntity<List<BootcampListResponse>> responseEntity = bootcampRestControllerAdapter.getAllBootcamps(page, size, Optional.of(ascending), Optional.of(orderBy));

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bootcampListResponses, responseEntity.getBody());
    }
}