package com.example.microserviciobootcamp.configuration;

import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter.AbilityAdapter;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.adapter.VersionBootcampAdapter;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IAbilityEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.mapper.IVersionBootcampEntityMapper;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IAbilityRepository;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.microserviciobootcamp.adapters.driven.jpa.mysql.repository.IVersionBootcampRepository;
import com.example.microserviciobootcamp.domain.api.IAbilityServicePort;
import com.example.microserviciobootcamp.domain.api.IBootcampServicePort;
import com.example.microserviciobootcamp.domain.api.ITechnologyServicePort;
import com.example.microserviciobootcamp.domain.api.IVersionBootcampServicePort;
import com.example.microserviciobootcamp.domain.api.usecase.AbilityUseCase;
import com.example.microserviciobootcamp.domain.api.usecase.BootcampUseCase;
import com.example.microserviciobootcamp.domain.api.usecase.TechnologyUseCase;
import com.example.microserviciobootcamp.domain.api.usecase.VersionBootcampUseCase;
import com.example.microserviciobootcamp.domain.spi.IAbilityPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IBootcampPersistencePort;
import com.example.microserviciobootcamp.domain.spi.ITechnologyPersistencePort;
import com.example.microserviciobootcamp.domain.spi.IVersionBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    private final IAbilityEntityMapper abilityEntityMapper;
    private final IAbilityRepository abilityRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    private final IBootcampRepository bootcampRepository;
    private final IVersionBootcampEntityMapper versionBootcampEntityMapper;
    private final IVersionBootcampRepository versionBootcampRepository;


    @Bean
    public ITechnologyPersistencePort technologyPersistencePort(){
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public IAbilityPersistencePort abilityPersistencePort(){
        return new AbilityAdapter(abilityRepository, abilityEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public IAbilityServicePort abilityServicePort(){
        return new AbilityUseCase(abilityPersistencePort(), technologyPersistencePort());
    }

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }

    @Bean
    public IBootcampServicePort bootcampServicePort(){
        return new BootcampUseCase(bootcampPersistencePort(), abilityPersistencePort());
    }

    @Bean
    public IVersionBootcampPersistencePort versionBootcampPersistencePort(){
        return new VersionBootcampAdapter(versionBootcampRepository, versionBootcampEntityMapper);
    }

    @Bean
    public IVersionBootcampServicePort versionBootcampServicePort(){
        return new VersionBootcampUseCase(versionBootcampPersistencePort(), bootcampPersistencePort());
    }
}
