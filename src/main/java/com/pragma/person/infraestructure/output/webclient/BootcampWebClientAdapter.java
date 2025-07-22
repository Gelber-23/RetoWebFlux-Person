package com.pragma.person.infraestructure.output.webclient;

import com.pragma.person.domain.model.web.Bootcamp;
import com.pragma.person.domain.spi.web.IBootcampClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BootcampWebClientAdapter implements IBootcampClientPort {

    private final WebClient webClient;

    public BootcampWebClientAdapter(WebClient.Builder builder, @Value("${client.bootcamp}") String bootcampUrl) {


        this.webClient = builder.baseUrl(bootcampUrl).build();
    }


    @Override
    public Mono<Bootcamp> getBootcampById(Long id) {
        return
                webClient.get()
                        .uri("/" + id)
                        .retrieve()
                        .bodyToMono(Bootcamp.class);
    }
}
