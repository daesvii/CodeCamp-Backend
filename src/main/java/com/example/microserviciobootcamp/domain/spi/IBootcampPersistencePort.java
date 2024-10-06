package com.example.microserviciobootcamp.domain.spi;

import com.example.microserviciobootcamp.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampPersistencePort {
    Bootcamp saveBootcamp (Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean ascending, String orderBy);
    boolean existsById(Long id);
}
