package com.example.microserviciobootcamp.adapters.driving.http.service.handler_impl;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.example.microserviciobootcamp.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.example.microserviciobootcamp.adapters.driving.http.service.interface_handler.ITechnologyHandler;
import com.example.microserviciobootcamp.domain.api.ITechnologyServicePort;
import com.example.microserviciobootcamp.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TechnologyHandlerImpl implements ITechnologyHandler {

    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;
    private final ITechnologyServicePort technologyServicePort;

    @Override
    public TechnologyResponse saveTechnology(AddTechnologyRequest request) {
        Technology technology = technologyRequestMapper.addRequestToTechnology(request);
        Technology savedTechnology = technologyServicePort.saveTechnology(technology);

        return technologyResponseMapper.toTechnologyResponse(savedTechnology);
    }

    @Override
    public List<TechnologyResponse> getAllTechnologies(Integer page, Integer size, boolean ascending) {
        List<Technology> listTechnologies = technologyServicePort.getAllTechnologies(page,size, ascending);
        return technologyResponseMapper.toTechnologyResponseList(listTechnologies);
    }

    @Override
    public TechnologyResponse getTechnology(String technologyName) {
        Technology technology = technologyServicePort.getTechnology(technologyName);
        return technologyResponseMapper.toTechnologyResponse(technology);
    }
}
