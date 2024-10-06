package com.example.microserviciobootcamp.domain.api.usecase;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.microserviciobootcamp.domain.exception.EndDateBeforeStartDateException;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IVersionBootcampPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VersionBootcampUseCaseTest {
    @Mock
    IVersionBootcampPersistencePort versionBootcampPersistencePort;

    @Mock
    IBootcampPersistencePort bootcampPersistencePort;

    @Mock
    EndDateBeforeStartDateException endDateBeforeStartDateException;

    @Mock
    ElementNotFoundException elementNotFoundException;

    @InjectMocks
    VersionBootcampUseCase versionBootcampUseCase;

    @Test
    void saveVersionBootcamp() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", new ArrayList<>());
        VersionBootcamp versionBootcamp = new VersionBootcamp(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), bootcamp);

        when(versionBootcampPersistencePort.saveVersionBootcamp(versionBootcamp)).thenReturn(versionBootcamp);
        when(bootcampPersistencePort.existsById(anyLong())).thenReturn(true);

        // Act
        VersionBootcamp savedVersionBootcamp = versionBootcampUseCase.saveVersionBootcamp(versionBootcamp);

        // Assert
        assertEquals(versionBootcamp, savedVersionBootcamp);
        verify(versionBootcampPersistencePort, times(1)).saveVersionBootcamp(versionBootcamp);
        verify(bootcampPersistencePort, times(1)).existsById(anyLong());
    }

    @Test
    void saveVersionBootcampWithEndDateBeforeStartDateException() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", new ArrayList<>());
        VersionBootcamp versionBootcamp = new VersionBootcamp(1L, 30, LocalDate.of(2024, 04, 10), LocalDate.of(2024, 04, 05), bootcamp);

        // Act y Assert
        assertThrows(endDateBeforeStartDateException.getClass(), () -> versionBootcampUseCase.saveVersionBootcamp(versionBootcamp));
    }

    @Test
    void saveVersionBootcampWithElementNotFoundException() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", new ArrayList<>());
        VersionBootcamp versionBootcamp = new VersionBootcamp(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), bootcamp);

        // Act y Assert
        assertThrows(elementNotFoundException.getClass(), () -> versionBootcampUseCase.saveVersionBootcamp(versionBootcamp));
    }
}