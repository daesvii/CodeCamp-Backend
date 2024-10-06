package com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.TechnologyResponse;

import java.util.List;

public interface ITechnologyHandler {
    TechnologyResponse saveTechnology(AddTechnologyRequest request);
    List<TechnologyResponse> getAllTechnologies(Integer page, Integer size, boolean ascending);
    TechnologyResponse getTechnology(String technologyName);
}