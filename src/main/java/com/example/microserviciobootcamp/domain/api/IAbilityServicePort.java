package com.example.microserviciobootcamp.domain.api;

import com.example.microserviciobootcamp.domain.model.Ability;

import java.util.List;

public interface IAbilityServicePort {
    Ability saveAbility (Ability ability);
    List<Ability> getAllAbilities(Integer page, Integer size, Boolean ascending, String orderBy);
}
