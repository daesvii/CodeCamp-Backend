package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "abilities", source = "abilityIds", qualifiedByName = "mapAbilityIdsToAbilities")
    Bootcamp addRequestToBootcamp(AddBootcampRequest bootcampRequest);

    @Named("mapAbilityIdsToAbilities")
    default List<Ability> mapAbilityIdsToAbilities(List<Long> abilityIds) {
        if (abilityIds == null || abilityIds.isEmpty()) {
            return Collections.emptyList();
        }
        return abilityIds.stream()
                .map(id -> new Ability(id, null, null, null))
                .toList();
    }
}
