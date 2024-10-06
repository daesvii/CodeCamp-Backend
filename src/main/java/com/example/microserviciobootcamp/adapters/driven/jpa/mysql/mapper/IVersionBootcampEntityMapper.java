package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.VersionBootcampEntity;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionBootcampEntityMapper {
    @Mapping(target = "bootcamp", source = "bootcamp")
    VersionBootcamp toModel(VersionBootcampEntity versionBootcampEntity);

    VersionBootcampEntity toEntity(VersionBootcamp versionBootcamp);

    List<VersionBootcamp> toModelList(List<VersionBootcampEntity> versionBootcampEntities);
}
