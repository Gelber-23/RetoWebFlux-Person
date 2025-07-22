package com.pragma.person.application.handler.impl;

import com.pragma.person.application.dto.request.PersonCreateRequest;
import com.pragma.person.application.dto.request.RegisterBootcampRequest;
import com.pragma.person.application.dto.response.PersonResponse;
import com.pragma.person.application.handler.IPersonHandler;
import com.pragma.person.application.mapper.request.IPersonCreateRequestMapper;
import com.pragma.person.application.mapper.request.IRegisterBootcampRequestMapper;
import com.pragma.person.application.mapper.response.IPersonResponseMapper;
import com.pragma.person.domain.api.IPersonServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
public class PersonHandler implements IPersonHandler {
    private final IPersonServicePort personServicePort;
    private final IPersonCreateRequestMapper personCreateRequestMapper;
    private final IRegisterBootcampRequestMapper registerBootcampRequestMapper;
    private final IPersonResponseMapper personResponseMapper;

    @Override
    public Mono<PersonResponse> registerInBootcamps(RegisterBootcampRequest registerBootcampRequest) {
        return Mono.just(registerBootcampRequest)
                .map(registerBootcampRequestMapper::toModel)
                .flatMap(personServicePort::registerInBootcamps)
                .map(personResponseMapper::toResponse);

    }

    @Override
    public Mono<PersonResponse> registerPerson(PersonCreateRequest person) {
        return Mono.just(person)
                .map(personCreateRequestMapper::toModel)
                .flatMap(personServicePort::registerPerson)
                .map(personResponseMapper::toResponse);
    }

    @Override
    public Mono<PersonResponse> getByPerson(Long personId) {
        return personServicePort.getByPersonId(personId)
                .map(personResponseMapper::toResponse);
    }

    @Override
    public Mono<Long> countByBootcampId(Long bootcampId) {
        return personServicePort.countByBootcampId(bootcampId);
    }
}
