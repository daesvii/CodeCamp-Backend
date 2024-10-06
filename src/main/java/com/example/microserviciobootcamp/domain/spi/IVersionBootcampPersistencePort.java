package com.example.microserviciobootcamp.domain.spi;

import com.example.microserviciobootcamp.domain.model.VersionBootcamp;

import java.util.List;

public interface IVersionBootcampPersistencePort {

    VersionBootcamp saveVersionBootcamp(VersionBootcamp versionBootcamp);
    List<VersionBootcamp> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy);
    List<VersionBootcamp> getAllVersionsBootcampsById(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId);
}
