package com.example.microserviciobootcamp.adapters.driving.http.controller;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl.VersionBootcampHandlerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VersionBootcampRestControllerAdapterTest {

    @Mock
    VersionBootcampHandlerImpl versionBootcampHandler;

    @InjectMocks
    VersionBootcampRestControllerAdapter versionBootcampRestControllerAdapter;

    @Test
    void addVersionBootcamp() {
        // Arrange
        AddVersionBootcampRequest request = new AddVersionBootcampRequest(30,  LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10),1L);
        VersionBootcampResponse response = new VersionBootcampResponse(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10),1L);

        when(versionBootcampHandler.saveVersionBootcamp(request)).thenReturn(response);

        // Act
        ResponseEntity<VersionBootcampResponse> result = versionBootcampRestControllerAdapter.addVersionBootcamp(request);

        // Result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}