package com.example.microserviciobootcamp.domain.spi;

import com.example.microserviciobootcamp.domain.model.Ability;

import java.util.List;
import java.util.Optional;

public interface IAbilityPersistencePort {
    Ability saveAbility (Ability ability);
    Optional<Ability> findAbilityByName (String name);
    List<Ability> getAllAbilities(Integer page, Integer size, boolean ascending, String orderBy);
    boolean existsById(Long id);
}
