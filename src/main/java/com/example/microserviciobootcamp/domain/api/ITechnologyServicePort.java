package com.example.microserviciobootcamp.domain.api;

import com.example.microserviciobootcamp.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    Technology saveTechnology (Technology technology);
    Technology getTechnology(String name);
    List<Technology> getAllTechnologies(Integer page, Integer size, Boolean ascending);
}
