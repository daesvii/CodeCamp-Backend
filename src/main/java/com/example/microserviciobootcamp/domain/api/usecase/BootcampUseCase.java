package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.api.IBootcampServicePort;
import com.example.microserviciobootcamp.domain.exception.DataRepeatsItselfException;
import com.example.microserviciobootcamp.domain.exception.FieldExceedsCharactersException;
import com.example.microserviciobootcamp.domain.exception.MinimumDataFieldMissingException;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import com.example.microserviciobootcamp.domain.util.DomainConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BootcampUseCase implements IBootcampServicePort {
    private final IBootcampPersistencePort bootcampPersistencePort;
    private final IAbilityPersistencePort abilityPersistencePort;

    public BootcampUseCase (IBootcampPersistencePort bootcampPersistencePort, IAbilityPersistencePort abilityPersistencePort){
        this.bootcampPersistencePort = bootcampPersistencePort;
        this.abilityPersistencePort = abilityPersistencePort;
    }
    @Override
    public Bootcamp saveBootcamp(Bootcamp bootcamp) {
        isAbilityRepeats(bootcamp);
        validateFields(bootcamp);
        doesNotAbilityExists(bootcamp);

        return bootcampPersistencePort.saveBootcamp(bootcamp);
    }

    private void validateFields (Bootcamp bootcamp){
        if (bootcamp.getAbilities().size() < DomainConstants.MIN_ABILITIES_SIZE){
            throw new MinimumDataFieldMissingException(DomainConstants.Field.ABILITY_IDS.toString());
        }
        if (bootcamp.getAbilities().size() > DomainConstants.MAX_ABILITIES_SIZE) {
            throw new FieldExceedsCharactersException(DomainConstants.Field.ABILITY_IDS.toString());
        }
    }

    private void doesNotAbilityExists (Bootcamp bootcamp){
        for (Ability ability : bootcamp.getAbilities()) {
            if (!abilityPersistencePort.existsById(ability.getId())) {
                throw new ElementNotFoundException(DomainConstants.Field.ABILITY_IDS.toString());
            }
        }
    }

    private void isAbilityRepeats (Bootcamp bootcamp){
        Set<Long> uniqueAbilities = new HashSet<>();
        for (Ability ability : bootcamp.getAbilities()) {
            if (!uniqueAbilities.add(ability.getId())) {
                throw new DataRepeatsItselfException(DomainConstants.Field.ABILITY_IDS.toString());
            }
        }
    }

    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, Boolean ascending, String orderBy) {
        return bootcampPersistencePort.getAllBootcamps(page, size, ascending, orderBy);
    }
}
