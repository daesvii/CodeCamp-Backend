package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.api.IVersionBootcampServicePort;
import com.example.microserviciobootcamp.domain.exception.EndDateBeforeStartDateException;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IVersionBootcampPersistencePort;
import com.example.microserviciobootcamp.domain.util.DomainConstants;

import java.util.List;

public class VersionBootcampUseCase implements IVersionBootcampServicePort {
    private final IVersionBootcampPersistencePort versionBootcampPersistencePort;
    private final IBootcampPersistencePort bootcampPersistencePort;

    public VersionBootcampUseCase (IVersionBootcampPersistencePort versionBootcampPersistencePort, IBootcampPersistencePort bootcampPersistencePort) {
        this.versionBootcampPersistencePort = versionBootcampPersistencePort;
        this.bootcampPersistencePort = bootcampPersistencePort;
    }

    @Override
    public VersionBootcamp saveVersionBootcamp(VersionBootcamp versionBootcamp) {
        validateDateFields(versionBootcamp);
        doesNotBootcampExists(versionBootcamp);

        return versionBootcampPersistencePort.saveVersionBootcamp(versionBootcamp);
    }

    @Override
    public List<VersionBootcamp> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId) {
        if(bootcampId == 0){
            return versionBootcampPersistencePort.getAllVersionsBootcamps(page, size, ascending, orderBy);
        }else {
            return versionBootcampPersistencePort.getAllVersionsBootcampsById(page, size, ascending, orderBy, bootcampId);
        }
    }

    private void validateDateFields (VersionBootcamp versionBootcamp) {
        if (versionBootcamp.getEndDate().isBefore(versionBootcamp.getStartDate())) {
            throw new EndDateBeforeStartDateException();
        }
    }

    private void doesNotBootcampExists (VersionBootcamp versionBootcamp) {
        if (!bootcampPersistencePort.existsById(versionBootcamp.getBootcamp().getId())) {
            throw new ElementNotFoundException(DomainConstants.Field.BOOTCAMP_ID.toString());
        }
    }
}
