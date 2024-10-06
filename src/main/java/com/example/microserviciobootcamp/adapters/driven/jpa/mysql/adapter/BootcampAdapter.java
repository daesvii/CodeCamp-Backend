package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public Bootcamp saveBootcamp(Bootcamp bootcamp) {
        BootcampEntity bootcampEntity = bootcampEntityMapper.toEntity(bootcamp);
        BootcampEntity bootcampSaved = bootcampRepository.save(bootcampEntity);
        return bootcampEntityMapper.toModel(bootcampSaved);
    }

    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean ascending, String orderBy) {
        List<BootcampEntity> bootcamps;
        if ("abilities".equals(orderBy)) {
            if (ascending) {
                bootcamps = bootcampRepository.findAllOrderByAbilitiesSizeAsc();
            } else {
                bootcamps = bootcampRepository.findAllOrderByAbilitiesSizeDesc();
            }
        } else {
            Pageable pageable = PageRequest.of(page, size, ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
            Page<BootcampEntity> abilitiesPage = bootcampRepository.findAll(pageable);
            bootcamps = abilitiesPage.getContent();
        }

        if (bootcamps.isEmpty()) {
            throw new NoDataFoundException();
        }
        return bootcampEntityMapper.toModelList(bootcamps);
    }

    @Override
    public boolean existsById(Long id) {return bootcampRepository.existsById(id);}
}
