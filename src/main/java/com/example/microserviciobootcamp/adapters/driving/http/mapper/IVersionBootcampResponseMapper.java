package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampListResponse;
import com.example.microserviciobootcamp.adapters.driving.http.dto.response.VersionBootcampResponse;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionBootcampResponseMapper {
    @Mapping(target = "bootcampId", source = "bootcamp", qualifiedByName = "mapBootcampToBootcampId")
    VersionBootcampResponse toVersionBootcampResponse(VersionBootcamp versionBootcamp);

    @Named("mapBootcampToBootcampId")
    default Long mapBootcampToBootcampId(Bootcamp bootcamp) {
        return bootcamp.getId();
    }

    List<VersionBootcampListResponse> toVersionBootcampResponseList(List<VersionBootcamp> versionBootcamps);
}
