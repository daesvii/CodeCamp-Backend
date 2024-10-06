package com.example.microserviciobootcamp.domain.spi;

import com.example.microserviciobootcamp.domain.model.Technology;

import java.util.List;
import java.util.Optional;

public interface ITechnologyPersistencePort {
    Technology saveTechnology (Technology technology);
    Optional<Technology> findTechnologyByName (String name);
    List<Technology> getAllTechnologies(Integer page, Integer size, boolean ascending);
    boolean existsById(Long id);
}
