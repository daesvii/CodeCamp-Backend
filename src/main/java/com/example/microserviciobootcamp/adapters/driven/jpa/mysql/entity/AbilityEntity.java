package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "ability_technology",
            joinColumns = @JoinColumn(name = "ability_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<TechnologyEntity> technologies;

    @ManyToMany(mappedBy = "abilities")
    private List<BootcampEntity> bootcamps;
}
