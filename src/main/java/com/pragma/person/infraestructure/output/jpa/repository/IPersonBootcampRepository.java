package com.pragma.person.infraestructure.output.jpa.repository;

import com.pragma.person.infraestructure.output.jpa.entity.PersonBootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonBootcampRepository extends ReactiveCrudRepository<PersonBootcampEntity, Long> {
    Flux<PersonBootcampEntity> findAllByPeopleId(Long personId);
    Mono<Long> countByPeopleId(Long personId);
    Mono<Long> countByBootcampId(Long bootcampId);
    Flux<PersonBootcampEntity> findAllByBootcampId(Long bootcampId);
}
