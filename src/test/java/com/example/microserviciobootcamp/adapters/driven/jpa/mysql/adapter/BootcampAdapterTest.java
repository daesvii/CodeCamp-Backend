package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.AbilityEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampAdapterTest {

    @Mock
    IBootcampRepository bootcampRepository;

    @Mock
    IBootcampEntityMapper bootcampEntityMapper;

    @Mock
    NoDataFoundException noDataFoundException;

    @InjectMocks
    BootcampAdapter bootcampAdapter;

    private List<BootcampEntity> bootcampEntities;
    private List<Bootcamp> bootcamps;
    @BeforeEach
    void setUp(){
        bootcampEntities = new ArrayList<>();
        BootcampEntity bootcampEntity1 = new BootcampEntity(1L, "Bootcamp1", "Description1", new ArrayList<>(), new ArrayList<>());
        BootcampEntity bootcampEntity2 = new BootcampEntity(2L, "Bootcamp2", "Description2", new ArrayList<>(), new ArrayList<>());
        bootcampEntities.add(bootcampEntity1);
        bootcampEntities.add(bootcampEntity2);

        bootcamps = new ArrayList<>();
        Bootcamp bootcamp1 = new Bootcamp(1L, "Bootcamp1", "Description1", new ArrayList<>());
        Bootcamp bootcamp2 = new Bootcamp(2L, "Bootcamp2", "Description2", new ArrayList<>());
        bootcamps.add(bootcamp1);
        bootcamps.add(bootcamp2);
    }

    @Test
    void saveBootcamp() {
        // Arrange
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(1L, "Ability", "description", new ArrayList<>()));
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp", "Description", abilities);

        List<AbilityEntity> abilityEntities = new ArrayList<>();
        abilityEntities.add(new AbilityEntity(1L, "Ability", "description", new ArrayList<>(), new ArrayList<>()));
        BootcampEntity bootcampEntity = new BootcampEntity(1L, "Bootcamp", "Description", abilityEntities, new ArrayList<>());

        when(bootcampEntityMapper.toEntity(bootcamp)).thenReturn(bootcampEntity);
        when(bootcampRepository.save(bootcampEntity)).thenReturn(bootcampEntity);
        when(bootcampEntityMapper.toModel(bootcampEntity)).thenReturn(bootcamp);

        // Act
        Bootcamp savedBootcamp = bootcampAdapter.saveBootcamp(bootcamp);

        // Assert
        assertNotNull(savedBootcamp);
        assertEquals(bootcamp.getId(), savedBootcamp.getId());
        assertEquals(bootcamp.getName(), savedBootcamp.getName());
        assertEquals(bootcamp.getDescription(), savedBootcamp.getDescription());

        verify(bootcampEntityMapper).toEntity(bootcamp);
        verify(bootcampRepository).save(bootcampEntity);
        verify(bootcampEntityMapper).toModel(bootcampEntity);
    }

    @Test
    void getAllBootcamps() {
        // Arrange
        when(bootcampRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(bootcampEntities));
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);

        // Act
        List<Bootcamp> result = bootcampAdapter.getAllBootcamps(0, 10, true, "name");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(bootcamps.get(0)));
        assertTrue(result.contains(bootcamps.get(1)));
    }

    @Test
    void getAllBootcampsOrderedByAbilitiesSizeAsc() {
        // Arrange
        when(bootcampRepository.findAllOrderByAbilitiesSizeAsc()).thenReturn(bootcampEntities);
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);

        // Act
        List<Bootcamp> result = bootcampAdapter.getAllBootcamps(0, 10, true, "abilities");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(bootcamps.get(0)));
        assertTrue(result.contains(bootcamps.get(1)));
    }

    @Test
    void getAllBootcampsOrderedByAbilitiesSizeDesc() {
        // Arrange
        when(bootcampRepository.findAllOrderByAbilitiesSizeDesc()).thenReturn(bootcampEntities);
        when(bootcampEntityMapper.toModelList(bootcampEntities)).thenReturn(bootcamps);

        // Act
        List<Bootcamp> result = bootcampAdapter.getAllBootcamps(0, 10, false, "abilities");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(bootcamps.get(0)));
        assertTrue(result.contains(bootcamps.get(1)));
    }

    @Test
    void getAllBootcampsWithEmptyListAndNoDataFoundException() {
        // Arrange
        when(bootcampRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        // Act and Assert
        assertThrows(noDataFoundException.getClass(), () -> bootcampAdapter.getAllBootcamps(0, 10, true, "name"));
    }
}