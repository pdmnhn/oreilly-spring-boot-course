package org.example.demo.services;

import org.example.demo.json.geocoder.Site;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GeocoderServiceTest {
    private final Logger logger = LoggerFactory.getLogger(GeocoderServiceTest.class);
    @Autowired
    private GeocoderService geocoderService;

    @Test
    public void getLatLndWithoutStreet() {
        Site site = this.geocoderService.getLatLong("Boston", "MA");
        logger.info(site.toString());
        assertAll(
                () -> assertEquals(100.0, site.getLatitude(), 0.01),
                () -> assertEquals(100.0, site.getLatitude(), 0.01)
        );
    }
}
