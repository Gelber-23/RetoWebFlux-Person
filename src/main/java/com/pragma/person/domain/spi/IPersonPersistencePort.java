package com.pragma.person.domain.spi;

import com.pragma.person.domain.model.Person;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPersonPersistencePort {
    Mono<Person> registerInBootcamps(Long personId, List<Long> bootcampsId);
    Mono<Person> save(Person person);
    Mono<Long> countByPersonId(Long personId);
    Mono<Person> getByPersonID(Long personId);
    Mono<Long> countByBootcampId(Long bootcampId);
}
