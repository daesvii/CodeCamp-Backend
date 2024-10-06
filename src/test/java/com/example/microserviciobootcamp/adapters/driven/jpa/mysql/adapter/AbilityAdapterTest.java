package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.AbilityEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IAbilityEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IAbilityRepository;
import com.example.microserviciobootcamp.domain.model.Ability;
import com.example.microserviciobootcamp.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbilityAdapterTest {

    @Mock
    IAbilityEntityMapper abilityEntityMapper;

    @Mock
    IAbilityRepository abilityRepository;

    @Mock
    NoDataFoundException noDataFoundException;

    @InjectMocks
    AbilityAdapter abilityAdapter;

    private List<AbilityEntity> abilityEntities;

    private List<Ability> abilities;

    @BeforeEach
    void setUp() {
         abilityEntities = new ArrayList<>();
        AbilityEntity abilityEntity1 = new AbilityEntity(1L, "Ability1", "Description", new ArrayList<>(), new ArrayList<>());
        AbilityEntity abilityEntity2 = new AbilityEntity(2L, "Ability2", "Description", new ArrayList<>(), new ArrayList<>());
        abilityEntities.add(abilityEntity1);
        abilityEntities.add(abilityEntity2);

        abilities = new ArrayList<>();
        Ability ability1 = new Ability(1L, "Ability1", "Description", new ArrayList<>());
        Ability ability2 = new Ability(2L, "Ability2", "Description", new ArrayList<>());
        abilities.add(ability1);
        abilities.add(ability2);
    }

    @Test
    void saveAbility() {
        // Arrange
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "technology1", "description"));
        technologies.add(new Technology(2L, "technology2", "description"));
        technologies.add(new Technology(3L, "technology3", "description"));
        Ability inputAbility = new Ability(1L, "Ability", "description", technologies);

        List<TechnologyEntity> technologiesEntity = new ArrayList<>();
        technologiesEntity.add(new TechnologyEntity(1L, "technology1", "description", null));
        technologiesEntity.add(new TechnologyEntity(2L, "technology2", "description", null));
        technologiesEntity.add(new TechnologyEntity(3L, "technology3", "description", null));

        AbilityEntity mockedAbilityEntity = new AbilityEntity(1L, "Ability", "description", technologiesEntity, new ArrayList<>());
        AbilityEntity savedAbilityEntity = new AbilityEntity(1L, "Ability", "description", technologiesEntity, new ArrayList<>());

        when(abilityEntityMapper.toEntity(inputAbility)).thenReturn(mockedAbilityEntity);
        when(abilityEntityMapper.toModel(savedAbilityEntity)).thenReturn(inputAbility);
        when(abilityRepository.save(mockedAbilityEntity)).thenReturn(savedAbilityEntity);

        // Act
        Ability actualAbility = abilityAdapter.saveAbility(inputAbility);

        // Assert
        verify(abilityRepository).save(mockedAbilityEntity);
        verify(abilityEntityMapper).toEntity(inputAbility);
        verify(abilityEntityMapper).toModel(savedAbilityEntity);
        assertNotNull(actualAbility);
        assertEquals(inputAbility, actualAbility);
    }

    @Test
    void findAbilityByName() {
        // Arrange
        String abilityName = "Ability";
        AbilityEntity mockedAbilityEntity = new AbilityEntity(1L, abilityName, "description", null, null);
        Ability mockedAbility = new Ability(1L, abilityName, "description", null);

        when(abilityRepository.findByName(abilityName)).thenReturn(Optional.of(mockedAbilityEntity));
        when(abilityEntityMapper.toModel(mockedAbilityEntity)).thenReturn(mockedAbility);

        // Act
        Optional<Ability> actualOptionalAbility = abilityAdapter.findAbilityByName(abilityName);

        // Assert
        verify(abilityRepository).findByName(abilityName);
        verify(abilityEntityMapper).toModel(mockedAbilityEntity);
        assertTrue(actualOptionalAbility.isPresent());
        assertEquals(mockedAbility, actualOptionalAbility.get());
    }

    @Test
    void getAllAbilities() {
        // Arrange
        when(abilityRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(abilityEntities));
        when(abilityEntityMapper.toModelList(abilityEntities)).thenReturn(abilities);

        // Act
        List<Ability> result = abilityAdapter.getAllAbilities(0, 10, true, "name");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(abilities.get(0)));
        assertTrue(result.contains(abilities.get(1)));
    }

    @Test
    void getAllAbilitiesOrderedByTechnologiesSizeAsc() {
        // Arrange
        when(abilityRepository.findAllOrderByTechnologiesSizeAsc()).thenReturn(abilityEntities);
        when(abilityEntityMapper.toModelList(abilityEntities)).thenReturn(abilities);

        // Act
        List<Ability> result = abilityAdapter.getAllAbilities(0, 10, true, "technologies");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(abilities.get(0)));
        assertTrue(result.contains(abilities.get(1)));
    }

    @Test
    void getAllAbilitiesOrderedByTechnologiesSizeDesc() {
        // Arrange
        when(abilityRepository.findAllOrderByTechnologiesSizeDesc()).thenReturn(abilityEntities);
        when(abilityEntityMapper.toModelList(abilityEntities)).thenReturn(abilities);

        // Act
        List<Ability> result = abilityAdapter.getAllAbilities(0, 10, false, "technologies");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(abilities.get(0)));
        assertTrue(result.contains(abilities.get(1)));
    }

    @Test
    void getAllAbilitiesWithEmptyListAndNoDataFoundException() {
        // Arrange
        when(abilityRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Act and Assert
        assertThrows(noDataFoundException.getClass(), () -> abilityAdapter.getAllAbilities(0, 10, true, "name"));
    }

    @Test
    void existsById() {
        // Arrange
        Long id = 1L;
        final boolean trueResult = true;
        when(abilityRepository.existsById(anyLong())).thenReturn(trueResult);

        // Act
        boolean result = abilityAdapter.existsById(id);

        // Assert
        assertEquals(trueResult, result);
    }

    @Test
    void existsByIdNotExistsReturnFalse() {
        // Arrange
        Long id = 1L;
        final boolean falseResult = false;
        when(abilityRepository.existsById(anyLong())).thenReturn(falseResult);

        // Act
        boolean result = abilityAdapter.existsById(id);

        // Assert
        assertEquals(falseResult, result);
    }
}