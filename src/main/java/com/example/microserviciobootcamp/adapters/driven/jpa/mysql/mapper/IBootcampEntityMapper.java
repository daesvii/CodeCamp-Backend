package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    @Mapping(target = "abilities", source = "abilities")
    Bootcamp toModel(BootcampEntity bootcampEntity);

    @Mapping(target = "versionBootcamps" , ignore = true)
    BootcampEntity toEntity(Bootcamp bootcamp);

    List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);
}
