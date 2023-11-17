package org.example.demo.controllers;

import org.example.demo.json.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/greeting")
    public Greeting greet(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting("Hello, " + name + "!");
    }
}
