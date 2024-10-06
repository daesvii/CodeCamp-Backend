package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
    TechnologyResponse toTechnologyResponse (Technology technology);
    List<TechnologyResponse> toTechnologyResponseList(List<Technology> technologies);
}
