package com.example.microserviciobootcamp.domain.model;

import java.util.List;

public class Bootcamp {
    private final Long id;
    private final String name;
    private final String description;
    private final List<Ability> abilities;

    public Bootcamp(Long id, String name, String description, List<Ability> abilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.abilities = abilities;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Ability> getAbilities() { return abilities; }
}
