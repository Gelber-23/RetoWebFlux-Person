package com.pragma.person.application.handler;

import com.pragma.person.application.dto.request.PersonCreateRequest;
import com.pragma.person.application.dto.request.RegisterBootcampRequest;
import com.pragma.person.application.dto.response.PersonResponse;
import reactor.core.publisher.Mono;

public interface IPersonHandler {
    Mono<PersonResponse> registerInBootcamps(RegisterBootcampRequest registerBootcampRequest);
    Mono<PersonResponse> registerPerson(PersonCreateRequest person);
    Mono<PersonResponse> getByPerson(Long personId);
    Mono<Long> countByBootcampId(Long bootcampId);
}
