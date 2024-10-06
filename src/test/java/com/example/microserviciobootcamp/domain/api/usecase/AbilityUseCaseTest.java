package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.exception.DataRepeatsItselfException;
import com.example.microserviciobootcamp.domain.exception.FieldExceedsCharactersException;
import com.example.microserviciobootcamp.domain.exception.MinimumDataFieldMissingException;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import com.example.microserviciobootcamp.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbilityUseCaseTest {

    @Mock
    IAbilityPersistencePort abilityPersistencePort;

    @Mock
    ITechnologyPersistencePort technologyPersistencePort;

    @Mock
    DataRepeatsItselfException dataRepeatsItselfException;

    @Mock
    MinimumDataFieldMissingException minimumDataFieldMissingException;

    @Mock
    FieldExceedsCharactersException fieldExceedsCharactersException;

    @Mock
    ElementNotFoundException elementNotFoundException;

    @InjectMocks
    AbilityUseCase abilityUseCase;

    private List<Technology> technologies;

    @BeforeEach
    void setUp() {
        technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "technology1", "description"));
        technologies.add(new Technology(2L, "technology2", "description"));
        technologies.add(new Technology(3L, "technology3", "description"));
    }

    @Test
    void saveAbility() {
        // Arrange
        Ability ability = new Ability(1L, "Ability", "description", technologies);

        when(technologyPersistencePort.existsById(anyLong())).thenReturn(true);
        when(abilityPersistencePort.saveAbility(ability))
                .thenReturn(ability);

        // Act
        Ability savedAbility = abilityUseCase.saveAbility(ability);

        // Assert
        assertEquals(ability, savedAbility);
    }

    @Test
    void saveAbilityWithDataRepeatsItselfException() {
        // Arrange
        List<Technology> technologiesRepeat = new ArrayList<>();
        technologiesRepeat.add(new Technology(1L, "technology1", "description"));
        technologiesRepeat.add(new Technology(1L, "technology1", "description"));

        Ability ability = new Ability(1L, "Ability", "description", technologiesRepeat);

        // Act y Assert
        assertThrows(dataRepeatsItselfException.getClass(), ()-> abilityUseCase.saveAbility(ability));
    }

    @Test
    void saveAbilityWithMinimumDataFieldMissingException() {
        // Arrange
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "technology1", "description"));

        Ability ability = new Ability(1L, "Ability", "description", technologies);

        // Act y Assert
        assertThrows(minimumDataFieldMissingException.getClass(), ()-> abilityUseCase.saveAbility(ability));
    }

    @Test
    void saveAbilityWithFieldExceedsCharactersException() {
        // Arrange
        technologies.add(new Technology(4L, "technology4", "description"));
        technologies.add(new Technology(5L, "technology5", "description"));
        technologies.add(new Technology(6L, "technology6", "description"));
        technologies.add(new Technology(7L, "technology7", "description"));
        technologies.add(new Technology(8L, "technology8", "description"));
        technologies.add(new Technology(9L, "technology9", "description"));
        technologies.add(new Technology(10L, "technology10", "description"));
        technologies.add(new Technology(11L, "technology11", "description"));
        technologies.add(new Technology(12L, "technology12", "description"));
        technologies.add(new Technology(13L, "technology13", "description"));
        technologies.add(new Technology(14L, "technology14", "description"));
        technologies.add(new Technology(15L, "technology15", "description"));
        technologies.add(new Technology(16L, "technology16", "description"));
        technologies.add(new Technology(17L, "technology17", "description"));
        technologies.add(new Technology(18L, "technology18", "description"));
        technologies.add(new Technology(19L, "technology19", "description"));
        technologies.add(new Technology(20L, "technology20", "description"));
        technologies.add(new Technology(21L, "technology21", "description"));

        Ability ability = new Ability(1L, "Ability", "description", technologies);

        // Act y Assert
        assertThrows(fieldExceedsCharactersException.getClass(), ()-> abilityUseCase.saveAbility(ability));
    }

    @Test
    void saveAbilityWithElementNotFoundException() {
        // Arrange
        Ability ability = new Ability(1L, "Ability", "description", technologies);

        when(technologyPersistencePort.existsById(1L)).thenReturn(true);
        when(technologyPersistencePort.existsById(2L)).thenReturn(false);

        // Act y Assert
        assertThrows(elementNotFoundException.getClass(), ()-> abilityUseCase.saveAbility(ability));
    }

    @Test
    void getAllAbilities() {
        Integer page = 1;
        Integer size = 10;
        boolean ascending = true;
        String orderBy = "name";

        List<Ability> expectedAbilities = new ArrayList<>();
        expectedAbilities.add(new Ability(1L, "Ability 1", "description", technologies));
        expectedAbilities.add(new Ability(2L, "Ability 2", "description", technologies));

        when(abilityPersistencePort.getAllAbilities(page, size, ascending, orderBy))
                .thenReturn(expectedAbilities);

        // Act
        List<Ability> actualAbilities = abilityUseCase.getAllAbilities(page, size, ascending, orderBy);

        // Assert
        assertEquals(expectedAbilities.size(), actualAbilities.size());
        assertEquals(expectedAbilities, actualAbilities);
        verify(abilityPersistencePort, times(1)).getAllAbilities(page, size, ascending, orderBy);
    }
}