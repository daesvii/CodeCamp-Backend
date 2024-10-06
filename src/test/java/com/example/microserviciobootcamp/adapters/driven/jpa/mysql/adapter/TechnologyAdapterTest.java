package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyAdapterTest {

    @Mock
    ITechnologyRepository technologyRepository;

    @Mock
    ITechnologyEntityMapper technologyEntityMapper;

    @Mock
    NoDataFoundException noDataFoundException;

    @InjectMocks
    TechnologyAdapter technologyAdapter;

    @Test
    void saveTechnology() {
        // Arrange
        Technology technologyInput = new Technology(1L, "Technology 1", "Description");

        TechnologyEntity technologyEntity = new TechnologyEntity(1L, "Technology 1", "Description", new ArrayList<>());

        when(technologyEntityMapper.toEntity(technologyInput)).thenReturn(technologyEntity);
        when(technologyRepository.save(technologyEntity)).thenReturn(technologyEntity);
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technologyInput);

        // Act
        Technology savedTechnology = technologyAdapter.saveTechnology(technologyInput);

        // Assert
        assertNotNull(savedTechnology);
        assertEquals(technologyInput.getId(), savedTechnology.getId());
        assertEquals(technologyInput.getName(), savedTechnology.getName());
        assertEquals(technologyInput.getDescription(), savedTechnology.getDescription());

        verify(technologyEntityMapper).toEntity(technologyInput);
        verify(technologyRepository).save(technologyEntity);
        verify(technologyEntityMapper).toModel(technologyEntity);
    }

    @Test
    void findTechnologyByName() {
        // Arrange
        String technologyName = "Technology";
        TechnologyEntity technologyEntity = new TechnologyEntity(1L, technologyName, "Description", new ArrayList<>());

        when(technologyRepository.findByName(technologyName)).thenReturn(Optional.of(technologyEntity));

        Technology technology = new Technology(1L, technologyName, "Description");
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);

        // Act
        Optional<Technology> foundTechnology = technologyAdapter.findTechnologyByName(technologyName);

        // Assert
        assertTrue(foundTechnology.isPresent());
        assertEquals(technology, foundTechnology.get());

        verify(technologyRepository).findByName(technologyName);
        verify(technologyEntityMapper).toModel(technologyEntity);
    }

    @Test
    void testFindTechnologyByNameIfNotExists() {
        // Arrange
        String technologyName = "Technology";
        when(technologyRepository.findByName(technologyName)).thenReturn(Optional.empty());

        // Act
        Optional<Technology> result = technologyAdapter.findTechnologyByName(technologyName);

        // Assert
        assertTrue(result.isEmpty());
        verify(technologyRepository).findByName(technologyName);
    }

    @Test
    void getAllTechnologies() {
        // Arrange
        List<TechnologyEntity> technologyEntities = new ArrayList<>();
        TechnologyEntity technologyEntity1 = new TechnologyEntity(1L, "Java", "Description", new ArrayList<>());
        TechnologyEntity technologyEntity2 = new TechnologyEntity(2L, "Python", "Description", new ArrayList<>());
        technologyEntities.add(technologyEntity1);
        technologyEntities.add(technologyEntity2);

        List<Technology> expectedTechnologies = new ArrayList<>();
        expectedTechnologies.add(new Technology(1L, "Java", "Description"));
        expectedTechnologies.add(new Technology(2L, "Python", "Description"));

        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(expectedTechnologies);

        // Act
        List<Technology> result = technologyAdapter.getAllTechnologies(0, 10, true);

        // Assert
        assertNotNull(result);
        assertEquals(expectedTechnologies.size(), result.size());
        assertTrue(result.containsAll(expectedTechnologies));
    }

    @Test
    void testGetAllTechnologies_EmptyList() {
        // Arrange
        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act and Assert
        assertThrows(noDataFoundException.getClass(), () -> technologyAdapter.getAllTechnologies(0, 10, true));
    }

    @Test
    void existsById() {
        // Arrange
        Long id = 1L;
        final boolean trueResult = true;
        when(technologyRepository.existsById(anyLong())).thenReturn(trueResult);

        // Act
        boolean result = technologyAdapter.existsById(id);

        // Assert
        assertEquals(trueResult, result);
    }

    @Test
    void existsByIdNotExistsReturnFalse() {
        // Arrange
        Long id = 1L;
        final boolean falseResult = false;
        when(technologyRepository.existsById(anyLong())).thenReturn(falseResult);

        // Act
        boolean result = technologyAdapter.existsById(id);

        // Assert
        assertEquals(falseResult, result);
    }
}