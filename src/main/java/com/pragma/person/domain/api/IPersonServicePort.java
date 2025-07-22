package com.pragma.person.domain.api;

import com.pragma.person.domain.model.Person;
import com.pragma.person.domain.model.PersonRegisterBootcampRequest;
import reactor.core.publisher.Mono;

public interface IPersonServicePort {
    Mono<Person> registerInBootcamps(PersonRegisterBootcampRequest personRegisterBootcampRequest);
    Mono<Person> registerPerson(Person person);
    Mono<Person> getByPersonId (Long personId);
    Mono<Long> countByBootcampId(Long bootcampId);
}
