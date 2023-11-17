package org.example.demo.services;

import org.example.demo.json.astro.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class AstroService {
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    @Autowired
    public AstroService(RestTemplateBuilder rtBuilder, WebClient.Builder wcBuilder) {
        this.restTemplate = rtBuilder.build();
        this.webClient = wcBuilder.baseUrl("http://api.open-notify.org").build();
    }

    public Response getAstronautsRT() {
        return this.restTemplate.getForObject("http://api.open-notify.org/astros.json", Response.class);
    }

    public Response getAstronautsWC() {
        return this.webClient
                .get()
                .uri("/astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));
    }
}
