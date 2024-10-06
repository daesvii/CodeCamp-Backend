package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.microserviciobootcamp.domain.model.Technology;
import com.example.microserviciobootcamp.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public Technology saveTechnology(Technology technology) {
        TechnologyEntity technologyEntity = technologyEntityMapper.toEntity(technology);
         TechnologyEntity savedTechnology = technologyRepository.save(technologyEntity);
        return technologyEntityMapper.toModel(savedTechnology);
    }

    @Override
    public Optional<Technology> findTechnologyByName(String name) {
        return technologyRepository.findByName(name).map(technologyEntityMapper::toModel);
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean ascending) {
        Sort sort = ascending ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<TechnologyEntity> technologies = technologyRepository.findAll(pageable).getContent();
        if (technologies.isEmpty()){
            throw new NoDataFoundException();
        }
        return technologyEntityMapper.toModelList(technologies);
    }

    @Override
    public boolean existsById(Long id) {
        return technologyRepository.existsById(id);
    }
}
