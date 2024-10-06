package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.VersionBootcampEntity;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IVersionBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IVersionBootcampRepository;
import com.example.microserviciobootcamp.domain.model.Bootcamp;
import com.example.microserviciobootcamp.domain.model.VersionBootcamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VersionBootcampAdapterTest {

    @Mock
    IVersionBootcampEntityMapper versionBootcampEntityMapper;

    @Mock
    IVersionBootcampRepository versionBootcampRepository;

    @InjectMocks
    VersionBootcampAdapter versionBootcampAdapter;

    @Test
    void saveVersionBootcamp() {
        // Arrange
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp 1", "Description", new ArrayList<>());
        VersionBootcamp versionBootcamp = new VersionBootcamp(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), bootcamp);

        BootcampEntity bootcampEntity = new BootcampEntity(1L, "Bootcamp 1", "Description", new ArrayList<>(), new ArrayList<>());
        VersionBootcampEntity versionBootcampEntity = new VersionBootcampEntity(1L, 30, LocalDate.of(2024, 04, 05), LocalDate.of(2024, 04, 10), bootcampEntity);

        when(versionBootcampEntityMapper.toEntity(versionBootcamp)).thenReturn(versionBootcampEntity);
        when(versionBootcampRepository.save(versionBootcampEntity)).thenReturn(versionBootcampEntity);
        when(versionBootcampEntityMapper.toModel(versionBootcampEntity)).thenReturn(versionBootcamp);

        // Act
        VersionBootcamp result = versionBootcampAdapter.saveVersionBootcamp(versionBootcamp);

        // Assert
        assertEquals(versionBootcamp, result);
    }
    @Test
    void getAllVersionsBootcamps () {

    }

    @Test
    void getAllVersionsBootcampsById () {

    }
}