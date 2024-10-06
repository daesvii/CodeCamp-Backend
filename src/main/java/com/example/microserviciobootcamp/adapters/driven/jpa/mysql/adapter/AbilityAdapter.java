package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.AbilityEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IAbilityEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IAbilityRepository;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbilityAdapter implements IAbilityPersistencePort {
    private final IAbilityRepository abilityRepository;
    private final IAbilityEntityMapper abilityEntityMapper;
    @Override
    public Ability saveAbility(Ability ability) {
        AbilityEntity abilityEntity = abilityEntityMapper.toEntity(ability);
        AbilityEntity savedAbility = abilityRepository.save(abilityEntity);
        return abilityEntityMapper.toModel(savedAbility);
    }

    @Override
    public Optional<Ability> findAbilityByName(String name) {
        return abilityRepository.findByName(name).map(abilityEntityMapper::toModel);
    }

    @Override
    public List<Ability> getAllAbilities(Integer page, Integer size, boolean ascending, String orderBy) {
        List<AbilityEntity> abilities;
        if ("technologies".equals(orderBy)) {
            if (ascending) {
                abilities = abilityRepository.findAllOrderByTechnologiesSizeAsc();
            } else {
                abilities = abilityRepository.findAllOrderByTechnologiesSizeDesc();
            }
        } else {
            Pageable pageable = PageRequest.of(page, size, ascending ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending());
            Page<AbilityEntity> abilitiesPage = abilityRepository.findAll(pageable);
            abilities = abilitiesPage.getContent();
        }

        if (abilities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return abilityEntityMapper.toModelList(abilities);
    }

    @Override
    public boolean existsById(Long id) {
        return abilityRepository.existsById(id);
    }
}
