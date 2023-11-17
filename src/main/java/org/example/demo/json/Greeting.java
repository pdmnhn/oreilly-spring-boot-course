package org.example.demo.json;

import org.springframework.stereotype.Component;
@Component
public class Greeting {
    private String message;

    public Greeting() {
        this.message = "Auto-instantiated bean";
    }

    public Greeting(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
