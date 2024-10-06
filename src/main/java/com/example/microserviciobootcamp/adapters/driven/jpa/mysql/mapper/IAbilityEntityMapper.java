package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.AbilityEntity;
import com.example.microserviciobootcamp.domain.model.Ability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAbilityEntityMapper {
    @Mapping(target = "technologies", source = "technologies")
    Ability toModel(AbilityEntity abilityEntity);

    @Mapping(target = "bootcamps", ignore = true)
    AbilityEntity toEntity(Ability ability);

    List<Ability> toModelList(List<AbilityEntity> abilityEntities);
}
