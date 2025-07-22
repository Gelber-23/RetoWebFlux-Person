package com.pragma.person.domain.spi.web;

import com.pragma.person.domain.model.web.Bootcamp;
import reactor.core.publisher.Mono;

public interface IBootcampClientPort {
    Mono<Bootcamp> getBootcampById(Long id);
}
