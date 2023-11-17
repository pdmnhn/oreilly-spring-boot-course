package org.example.demo.services;

import org.example.demo.json.astro.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class AstroServiceTest {
    @Autowired
    private AstroService astroService;

    @Test
    public void testServiceRT() {
        Response response = astroService.getAstronautsRT();
        assertNotNull(response);
        assertEquals(response.getNumber(), response.getPeople().size());
        response.getPeople().forEach(System.out::println);
    }

    @Test
    public void testServiceWC() {
        Response response = astroService.getAstronautsWC();
        assertNotNull(response);
        assertEquals(response.getNumber(), response.getPeople().size());
        response.getPeople().forEach(System.out::println);
    }
}
