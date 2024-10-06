package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    Technology toModel(TechnologyEntity technologyEntity);

    @Mapping(target = "abilities", ignore = true)
    TechnologyEntity toEntity(Technology technology);

    List<Technology> toModelList(List<TechnologyEntity> technologyEntities);
}
