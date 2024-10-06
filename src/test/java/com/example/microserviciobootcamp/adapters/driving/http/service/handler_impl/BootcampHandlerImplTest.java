package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.example.microserviciobootcamp.domain.api.IBootcampServicePort;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampHandlerImplTest {

    @Mock
    IBootcampRequestMapper bootcampRequestMapper;

    @Mock
    IBootcampResponseMapper bootcampResponseMapper;

    @Mock
    IBootcampServicePort bootcampServicePort;

    @InjectMocks
    BootcampHandlerImpl bootcampHandler;

    @Test
    void saveBootcamp() {
        // Arrange
        AddBootcampRequest request = new AddBootcampRequest("Bootcamp", "Description", List.of(1L, 2L, 3L));

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(1L, "Ability 1", "Description", new ArrayList<>()));
        abilities.add(new Ability(2L, "Ability 2", "Description", new ArrayList<>()));
        abilities.add(new Ability(3L, "Ability 3", "Description", new ArrayList<>()));

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp", "Description", abilities);
        BootcampResponse expectedResponse = new BootcampResponse(1L, "Bootcamp", "Description", List.of(1L, 2L, 3L));

        when(bootcampRequestMapper.addRequestToBootcamp(request)).thenReturn(bootcamp);
        when(bootcampServicePort.saveBootcamp(bootcamp)).thenReturn(bootcamp);
        when(bootcampResponseMapper.toBootcampResponse(bootcamp)).thenReturn(expectedResponse);

        // Act
        BootcampResponse actualResponse = bootcampHandler.saveBootcamp(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllBootcamps() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = false;
        String orderBy = "name";

        List<Bootcamp> bootcamps = Arrays.asList(
                new Bootcamp(1L, "Bootcamp1", "Description1", new ArrayList<>()),
                new Bootcamp(2L, "Bootcamp2", "Description2", new ArrayList<>())
        );

        List<BootcampListResponse> expectedResponse = Arrays.asList(
                new BootcampListResponse(1L, "Bootcamp1", "Description1", new ArrayList<>()),
                new BootcampListResponse(2L, "Bootcamp2", "Description2", new ArrayList<>())
        );

        when(bootcampServicePort.getAllBootcamps(page, size, ascending, orderBy)).thenReturn(bootcamps);
        when(bootcampResponseMapper.toBootcampResponseList(bootcamps)).thenReturn(expectedResponse);

        // Act
        List<BootcampListResponse> actualResponse = bootcampHandler.getAllBootcamps(page, size, ascending, orderBy);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }
}