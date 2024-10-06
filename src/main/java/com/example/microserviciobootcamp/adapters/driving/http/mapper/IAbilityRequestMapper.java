package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddAbilityRequest;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IAbilityRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technologies", source = "technologyIds", qualifiedByName = "mapTechnologyIdsToTechnologies")
    Ability addRequestToAbility(AddAbilityRequest addAbilityRequest);

    @Named("mapTechnologyIdsToTechnologies")
    default List<Technology> mapTechnologyIdsToTechnologies(List<Long> technologyIds) {
        if (technologyIds == null || technologyIds.isEmpty()) {
            return Collections.emptyList();
        }
        return technologyIds.stream()
                .map(id -> new Technology(id, "Default Name", "Default Description"))
                .toList();
    }
}
