package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity.VersionBootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVersionBootcampRepository extends JpaRepository<VersionBootcampEntity, Long> {
    Page<VersionBootcampEntity> findByBootcampId(Long bootcampId, Pageable pageable);
    @Query("SELECT a FROM VersionBootcampEntity a ORDER BY a.maxCapacity ASC")
    List<VersionBootcampEntity> findAllByMaxCapacityAsc();

    @Query("SELECT a FROM VersionBootcampEntity a ORDER BY a.maxCapacity DESC")
    List<VersionBootcampEntity> findAllByMaxCapacityDesc();

}
