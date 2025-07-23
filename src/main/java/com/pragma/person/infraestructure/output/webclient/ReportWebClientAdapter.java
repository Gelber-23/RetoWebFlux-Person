package com.pragma.person.infraestructure.output.webclient;

import com.pragma.person.domain.model.web.PersonInfoReport;
import com.pragma.person.domain.spi.web.IReportClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReportWebClientAdapter implements IReportClientPort {
    private final WebClient webClient;

    public ReportWebClientAdapter(WebClient.Builder builder,
                                  @Value("${client.report}") String reportUrl) {
        this.webClient = builder.baseUrl(reportUrl).build();
    }

    @Override
    public Mono<Void> createDataPersonReport(PersonInfoReport evt) {
        return webClient.post()
                .uri("/person")
                .bodyValue(evt)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
