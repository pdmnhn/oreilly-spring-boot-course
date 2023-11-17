package org.example.demo.services;

import org.example.demo.json.geocoder.Location;
import org.example.demo.json.geocoder.Response;
import org.example.demo.json.geocoder.Result;
import org.example.demo.json.geocoder.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {
    private final Logger logger = LoggerFactory.getLogger(GeocoderService.class);
    private final static String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";
    private final WebClient webClient;

    @Autowired
    public GeocoderService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://maps.googleapis.com").build();
    }

    public Site getLatLong(String... address) {
        String encoded = Stream.of(address)
                .map(s -> URLEncoder.encode(s, StandardCharsets.UTF_8))
                .collect(Collectors.joining(","));
        Response response = this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/maps/api/geocode/json")
                        .queryParam("address", encoded)
                        .queryParam("key", KEY)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));
        if(response != null) {
            logger.info(response.getStatus());
            if(response.getResults().isEmpty()) {
                logger.error("Results length is 0");
            } else {
                Result result = response.getResults().get(0);
                Location location = result.getGeometry().getLocation();
                return new Site(result.getFormattedAddress(), location.getLat(), location.getLng());
            }
        }

        throw new RuntimeException("The result of the REST API response is null or empty");
    }
}
