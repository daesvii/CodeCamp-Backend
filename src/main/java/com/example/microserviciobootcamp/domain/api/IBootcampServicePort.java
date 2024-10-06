package com.example.microserviciobootcamp.domain.api;

import com.example.microserviciobootcamp.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    Bootcamp saveBootcamp (Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps(Integer page, Integer size, Boolean ascending, String orderBy);
}
