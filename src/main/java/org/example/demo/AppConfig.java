package org.example.demo;

import org.example.demo.json.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Greeting defaultGreeting() {
        return new Greeting("Hello, World!");
    }

    @Bean
    public Greeting myGreeting() {
        return new Greeting("My greeting");
    }
}
