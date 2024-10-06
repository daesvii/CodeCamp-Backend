package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IVersionBootcampRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IVersionBootcampResponseMapper;
import com.example.microserviciobootcamp.domain.api.IVersionBootcampServicePort;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VersionBootcampHandlerImplTest {

    @Mock
    IVersionBootcampRequestMapper versionBootcampRequestMapper;

    @Mock
    IVersionBootcampResponseMapper versionBootcampResponseMapper;

    @Mock
    IVersionBootcampServicePort versionBootcampServicePort;

    @InjectMocks
    VersionBootcampHandlerImpl versionBootcampHandlerImpl;



    @Test
    void saveVersionBootcamp() {
        // Arrange
        AddVersionBootcampRequest request = new AddVersionBootcampRequest(30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), 1L);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", new ArrayList<>());
        VersionBootcamp versionBootcamp = new VersionBootcamp(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), bootcamp);
        VersionBootcampResponse expectedResponse = new VersionBootcampResponse(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), 1L);


        when(versionBootcampRequestMapper.toVersionBootcamp(request)).thenReturn(versionBootcamp);
        when(versionBootcampServicePort.saveVersionBootcamp(versionBootcamp)).thenReturn(versionBootcamp);
        when(versionBootcampResponseMapper.toVersionBootcampResponse(versionBootcamp)).thenReturn(expectedResponse);

        // Act
        VersionBootcampResponse response = versionBootcampHandlerImpl.saveVersionBootcamp(request);

        // Assert
        assertEquals(expectedResponse, response);
        verify(versionBootcampRequestMapper, times(1)).toVersionBootcamp(request);
        verify(versionBootcampServicePort, times(1)).saveVersionBootcamp(versionBootcamp);
    }

    @Test
    void getAllVersionsBootcamps() {
    }
}