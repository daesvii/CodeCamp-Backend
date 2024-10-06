package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.api.IAbilityServicePort;
import com.example.microserviciobootcamp.domain.exception.FieldExceedsCharactersException;
import com.example.microserviciobootcamp.domain.exception.MinimumDataFieldMissingException;
import com.example.microserviciobootcamp.domain.exception.DataRepeatsItselfException;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import com.example.microserviciobootcamp.domain.spi.ITechnologyPersistencePort;
import com.example.microserviciobootcamp.domain.util.DomainConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbilityUseCase implements IAbilityServicePort {
    private final IAbilityPersistencePort abilityPersistencePort;

    private final ITechnologyPersistencePort technologyPersistencePort;

    public AbilityUseCase(IAbilityPersistencePort abilityPersistencePort, ITechnologyPersistencePort technologyPersistencePort) {
        this.abilityPersistencePort = abilityPersistencePort;
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Ability saveAbility(Ability ability) {
        isTechnologyRepeats(ability);
        validateFields(ability);
        doesNotTechnologyExists(ability);

        return abilityPersistencePort.saveAbility(ability);
    }

    private void validateFields (Ability ability){
        if (ability.getTechnologies().size() < DomainConstants.MIN_TECHNOLOGIES_SIZE){
            throw new MinimumDataFieldMissingException(DomainConstants.Field.TECHNOLOGY_IDS.toString());
        }
        if (ability.getTechnologies().size() > DomainConstants.MAX_TECHNOLOGIES_SIZE) {
            throw new FieldExceedsCharactersException(DomainConstants.Field.TECHNOLOGY_IDS.toString());
        }
    }

    private void isTechnologyRepeats (Ability ability){
        Set<Long> uniqueTechnologies = new HashSet<>();
        for (Technology technology : ability.getTechnologies()) {
            if (!uniqueTechnologies.add(technology.getId())) {
                throw new DataRepeatsItselfException(DomainConstants.Field.TECHNOLOGY_IDS.toString());
            }
        }
    }

    private void doesNotTechnologyExists (Ability ability){
        for (Technology technology : ability.getTechnologies()) {
            if (!technologyPersistencePort.existsById(technology.getId())) {
                throw new ElementNotFoundException(DomainConstants.Field.TECHNOLOGY_IDS.toString());
            }
        }
    }

    @Override
    public List<Ability> getAllAbilities(Integer page, Integer size, Boolean ascending, String orderBy) {
        return abilityPersistencePort.getAllAbilities(page, size, ascending, orderBy);
    }
}
