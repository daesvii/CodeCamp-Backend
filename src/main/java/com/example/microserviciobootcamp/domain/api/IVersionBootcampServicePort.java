package com.example.microserviciobootcamp.domain.api;

import com.example.microserviciobootcamp.domain.model.VersionBootcamp;

import java.util.List;

public interface IVersionBootcampServicePort {

    VersionBootcamp saveVersionBootcamp(VersionBootcamp versionBootcamp);

    List<VersionBootcamp> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId);
}
