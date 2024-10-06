package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.VersionBootcampEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.InvalidOrderByException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IVersionBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IVersionBootcampRepository;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import com.example.microserviciobootcamp.domain.spi.IVersionBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class VersionBootcampAdapter implements IVersionBootcampPersistencePort {
    private final IVersionBootcampRepository versionBootcampRepository;
    private final IVersionBootcampEntityMapper versionBootcampEntityMapper;

    @Override
    public VersionBootcamp saveVersionBootcamp(VersionBootcamp versionBootcamp) {
        VersionBootcampEntity versionBootcampEntity = versionBootcampEntityMapper.toEntity(versionBootcamp);
        versionBootcampEntity = versionBootcampRepository.save(versionBootcampEntity);

        return versionBootcampEntityMapper.toModel(versionBootcampEntity);
    }

    @Override
    public List<VersionBootcamp> getAllVersionsBootcamps(Integer page, Integer size, boolean ascending, String orderBy) {
        try {
            Pageable pageable;

            pageable = PageRequest.of(page, size, ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());

            Page<VersionBootcampEntity> versionBootcampPage;
            versionBootcampPage = versionBootcampRepository.findAll(pageable);

            pageIsEmpty(versionBootcampPage);

            return versionBootcampEntityMapper.toModelList(versionBootcampPage.getContent());
        }catch (Exception e){
            throw new InvalidOrderByException(orderBy);
        }
    }

    @Override
    public List<VersionBootcamp> getAllVersionsBootcampsById(Integer page, Integer size, boolean ascending, String orderBy, Long bootcampId) {
        Pageable pageable = PageRequest.of(page, size, ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
        Page<VersionBootcampEntity> versionsBootcampsPage = versionBootcampRepository.findByBootcampId(bootcampId, pageable);

        pageIsEmpty(versionsBootcampsPage);

        return versionBootcampEntityMapper.toModelList(versionsBootcampsPage.getContent());
    }

    protected void pageIsEmpty(Page<VersionBootcampEntity> versionBootcampPage) {
        if (versionBootcampPage.isEmpty()) {
            throw new NoDataFoundException();
        }
    }
}
