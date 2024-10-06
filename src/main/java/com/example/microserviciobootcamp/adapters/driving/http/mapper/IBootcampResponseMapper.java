package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.BootcampResponse;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {
    @Mapping(target = "abilityIds", source = "abilities", qualifiedByName = "mapAbilitiesToAbilityIds")
    BootcampResponse toBootcampResponse(Bootcamp bootcamp);

    @Named("mapAbilitiesToAbilityIds")
    default List<Long> mapAbilitiesToAbilityIds(List<Ability> abilities) {
        return abilities.stream()
                .map(Ability::getId)
                .toList();
    }

    List<BootcampListResponse> toBootcampResponseList(List<Bootcamp> bootcamps);
}
