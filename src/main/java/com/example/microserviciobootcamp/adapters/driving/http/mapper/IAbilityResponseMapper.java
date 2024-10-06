package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.AbilityResponse;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAbilityResponseMapper {
    @Mapping(target = "technologyIds", source = "technologies", qualifiedByName = "mapTechnologiesToTechnologyIds")
    AbilityResponse toAbilityResponse(Ability ability);
    List<AbilityListResponse> toAbilityResponseList(List<Ability> abilities);

    @Named("mapTechnologiesToTechnologyIds")
    default List<Long> mapTechnologiesToTechnologyIds(List<Technology> technologies) {
        return technologies.stream()
                .map(Technology::getId)
                .toList();
    }
}
