package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.exception.DataRepeatsItselfException;
import com.example.microserviciobootcamp.domain.exception.FieldExceedsCharactersException;
import com.example.microserviciobootcamp.domain.exception.MinimumDataFieldMissingException;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.Technology;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BootcampUseCaseTest {
    @Mock
    IBootcampPersistencePort bootcampPersistencePort;

    @Mock
    IAbilityPersistencePort abilityPersistencePort;

    @Mock
    DataRepeatsItselfException dataRepeatsItselfException;

    @Mock
    MinimumDataFieldMissingException minimumDataFieldMissingException;

    @Mock
    FieldExceedsCharactersException fieldExceedsCharactersException;

    @Mock
    ElementNotFoundException elementNotFoundException;

    @InjectMocks
    BootcampUseCase bootcampUseCase;

    private List<Technology> technologies;

    private Ability ability;

    private List<Ability> abilities;

    @BeforeEach
    void setUp() {
        technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "technology1", "description"));
        technologies.add(new Technology(2L, "technology2", "description"));
        technologies.add(new Technology(3L, "technology3", "description"));

        abilities = new ArrayList<>();
        ability = new Ability(1L, "Ability", "Description", technologies);
        abilities.add(ability);
    }

    @Test
    void saveBootcamp() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", abilities);

        when(abilityPersistencePort.existsById(anyLong())).thenReturn(true);
        when(bootcampPersistencePort.saveBootcamp(bootcamp)).thenReturn(bootcamp);

        // Act
        Bootcamp savedBootcamp = bootcampUseCase.saveBootcamp(bootcamp);

        // Assert
        assertEquals(bootcamp, savedBootcamp);
    }

    @Test
    void saveBootcampWithDataRepeatsItselfException() {
        // Arrange
        List<Ability> abilitiesRepeat = new ArrayList<>();
        abilitiesRepeat.add(ability);
        abilitiesRepeat.add(ability);

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", abilitiesRepeat);

        // Act y Assert
        assertThrows(dataRepeatsItselfException.getClass(), ()-> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void saveBootcampWithMinimumDataFieldMissingException() {
        // Arrange
        List<Ability> abilityListEmpty = new ArrayList<>();

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", abilityListEmpty);

        // Act y Assert
        assertThrows(minimumDataFieldMissingException.getClass(), ()-> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void saveBootcampWithFieldExceedsCharactersException() {
        // Arrange
        abilities.add(new Ability(2L, "Ability", "Description", technologies));
        abilities.add(new Ability(3L, "Ability", "Description", technologies));
        abilities.add(new Ability(4L, "Ability", "Description", technologies));
        abilities.add(new Ability(5L, "Ability", "Description", technologies));

        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", abilities);

        // Act y Assert
        assertThrows(fieldExceedsCharactersException.getClass(), ()-> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void saveBootcampWithElementNotFoundException() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Ability", "description", abilities);

        when(abilityPersistencePort.existsById(1L)).thenReturn(false);

        // Act y Assert
        assertThrows(elementNotFoundException.getClass(), ()-> bootcampUseCase.saveBootcamp(bootcamp));
    }

    @Test
    void getAllBootcamps() {
        Integer page = 1;
        Integer size = 10;
        boolean ascending = true;
        String orderBy = "name";

        List<Bootcamp> expectedBootcamps = new ArrayList<>();
        expectedBootcamps.add(new Bootcamp(1L, "Bootcamp 1", "Description", abilities));
        expectedBootcamps.add(new Bootcamp(2L, "Bootcamp 2", "Description", abilities));

        when(bootcampPersistencePort.getAllBootcamps(page, size, ascending, orderBy))
                .thenReturn(expectedBootcamps);

        // Act
        List<Bootcamp> actualBootcamps = bootcampUseCase.getAllBootcamps(page, size, ascending, orderBy);

        // Assert
        assertEquals(expectedBootcamps.size(), actualBootcamps.size());
        assertEquals(expectedBootcamps, actualBootcamps);
        verify(bootcampPersistencePort, times(1)).getAllBootcamps(page, size, ascending, orderBy);
    }
}