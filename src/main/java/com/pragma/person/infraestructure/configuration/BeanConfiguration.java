package com.pragma.person.infraestructure.configuration;


import com.pragma.person.domain.api.IPersonServicePort;
import com.pragma.person.domain.spi.IPersonPersistencePort;
import com.pragma.person.domain.usecase.PersonUseCase;
import com.pragma.person.infraestructure.output.jpa.adapter.PersonJpaAdapter;
import com.pragma.person.infraestructure.output.jpa.mapper.IPersonEntityMapper;
import com.pragma.person.infraestructure.output.jpa.repository.IPersonBootcampRepository;
import com.pragma.person.infraestructure.output.jpa.repository.IPersonEntityRepository;
import com.pragma.person.infraestructure.output.webclient.BootcampWebClientAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPersonEntityRepository personEntityRepository;
    private final IPersonEntityMapper personEntityMapper;
    private final IPersonBootcampRepository personBootcampRepository;
    private final BootcampWebClientAdapter bootcampWebClientAdapter;

    @Bean
    public IPersonPersistencePort personPersistencePort(){
        return new PersonJpaAdapter(personEntityRepository, personBootcampRepository, personEntityMapper,bootcampWebClientAdapter);
    }

    @Bean
    public IPersonServicePort personServicePort(){
        return new PersonUseCase(personPersistencePort(),bootcampWebClientAdapter);
    }
}
