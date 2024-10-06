package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
    @Query("SELECT a FROM BootcampEntity a ORDER BY SIZE(a.abilities) ASC")
    List<BootcampEntity> findAllOrderByAbilitiesSizeAsc();

    @Query("SELECT a FROM BootcampEntity a ORDER BY SIZE(a.abilities) DESC")
    List<BootcampEntity> findAllOrderByAbilitiesSizeDesc();
}
