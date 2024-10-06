package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NonNullApi
@Repository
public interface ITechnologyRepository extends JpaRepository<TechnologyEntity, Long> {
    Optional<TechnologyEntity> findByName(String name);
    Page<TechnologyEntity> findAll(Pageable pageable);
    boolean existsById(Long id);
}
