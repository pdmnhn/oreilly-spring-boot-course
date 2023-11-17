package org.example.demo.controllers;

import org.example.demo.json.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerFunctionalTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void greetingTestAsObject() {
        Greeting response = template.getForObject("/greeting?name=Padmanabhan", Greeting.class);
        assertEquals("Hello, Padmanabhan!", response.getMessage());
    }

    @Test
    public void greetingTestAsEntity() {
        ResponseEntity<Greeting> entity = template.getForEntity("/greeting", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if(response != null) {
            assertEquals("Hello, World!", response.getMessage());
        }
    }
}
