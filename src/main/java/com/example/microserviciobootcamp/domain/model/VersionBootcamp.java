package com.example.microserviciobootcamp.domain.model;

import java.time.LocalDate;

public class VersionBootcamp {
    private final Long id;
    private final int maxCapacity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Bootcamp bootcamp;
    public VersionBootcamp(Long id, int maxCapacity, LocalDate startDate, LocalDate endDate, Bootcamp bootcamp) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bootcamp = bootcamp;
    }
    public Long getId() { return id; }
    public int getMaxCapacity() { return maxCapacity; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public Bootcamp getBootcamp() { return bootcamp; }
}
