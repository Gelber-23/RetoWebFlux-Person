package com.pragma.person.domain.spi.web;

import com.pragma.person.domain.model.web.PersonInfoReport;
import reactor.core.publisher.Mono;

public interface IReportClientPort {
    Mono<Void> createDataPersonReport(PersonInfoReport personInfoReport);
}
