package com.example.microserviciobootcamp.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bootcamp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootcampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bootcamp_ability",
            joinColumns = @JoinColumn(name = "bootcamp_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private List<AbilityEntity> abilities;

    @OneToMany(mappedBy = "bootcamp", cascade = CascadeType.ALL)
    private List<VersionBootcampEntity> versionBootcamps;
}
