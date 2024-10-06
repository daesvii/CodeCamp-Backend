package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.domain.exception.TechnologyAlreadyExistsException;
import com.example.microserviciobootcamp.domain.model.Technology;
import com.example.microserviciobootcamp.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {
    @Mock
    TechnologyAlreadyExistsException technologyAlreadyExistsException;

    @Mock
    ITechnologyPersistencePort technologyPersistencePort;

    @Mock
    NoDataFoundException noDataFoundException;

    @InjectMocks
    TechnologyUseCase technologyUseCase;

    private Technology technology;

    @BeforeEach
    void setUp() {
        technology = new Technology(1L, "technology", "description");
    }

    @Test
    void saveTechnology() {
        // Arrange - Desclaracion variables necesarias
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.empty());
        when(technologyPersistencePort.saveTechnology(technology)).thenReturn(technology);

        // Act - Se ejecuta el metodo que se va a probar
        Technology result = technologyUseCase.saveTechnology(technology);

        // Assert - Comprobar el resultado
        assertEquals(technology, result);
    }

    @Test
    void saveTechnologyWithTechnologyAlreadyExistsException() {
        // Arrange - Desclaracion variables necesarias
        when(technologyPersistencePort.findTechnologyByName(technology.getName())).thenReturn(Optional.of(technology));

        // Act y Assert
        assertThrows(technologyAlreadyExistsException.getClass(), ()-> technologyUseCase.saveTechnology(technology));
    }

    @Test
    void getTechnologyWhenTechnologyExists() {
        // Arrange
        String technologyName = "Java";
        Technology expectedTechnology = new Technology(1L, technologyName, "description");
        when(technologyPersistencePort.findTechnologyByName(technologyName))
                .thenReturn(Optional.of(expectedTechnology));

        // Act
        Technology actualTechnology = technologyUseCase.getTechnology(technologyName);

        // Assert
        assertEquals(expectedTechnology, actualTechnology);
    }

    @Test
    void getTechnologyWhenTechnologyDoesNotExist() {
        // Arrange
        String technologyName = "Python";
        when(technologyPersistencePort.findTechnologyByName(technologyName))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(noDataFoundException.getClass(), () -> {
            technologyUseCase.getTechnology(technologyName);
        });
    }

    @Test
    void getAllTechnologies() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascending = true;
        List<Technology> expectedTechnologies = new ArrayList<>();
        expectedTechnologies.add(new Technology(1L, "Java", "description"));
        expectedTechnologies.add(new Technology(1L, "Typescript", "description"));
        when(technologyPersistencePort.getAllTechnologies(page, size, ascending))
                .thenReturn(expectedTechnologies);

        // Act
        List<Technology> actualTechnologies = technologyUseCase.getAllTechnologies(page, size, ascending);

        // Assert
        assertEquals(expectedTechnologies.size(), actualTechnologies.size());
        for (int i = 0; i < expectedTechnologies.size(); i++) {
            assertEquals(expectedTechnologies.get(i), actualTechnologies.get(i));
        }
    }
}