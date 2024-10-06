package com.example.microserviciobootcamp.adapters.driving.http.mapper;

import com.example.microserviciobootcamp.adapters.driving.http.dto.request.AddVersionBootcampRequest;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IVersionBootcampRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bootcamp", source = "bootcampId", qualifiedByName = "mapBootcampIdToBootcamp")
    VersionBootcamp toVersionBootcamp(AddVersionBootcampRequest addVersionBootcampRequest);

    @Named("mapBootcampIdToBootcamp")
    default Bootcamp mapBootcampIdToBootcamp(Long bootcampId) {
        return new Bootcamp(bootcampId, null, null, null);
    }
}
