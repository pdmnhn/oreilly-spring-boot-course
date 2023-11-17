package org.example.demo.json.astro;

import java.util.List;

public class Response {
    private int number;
    private List<Astro> people;
    private String message;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public  List<Astro> getPeople() {
        return people;
    }

    public void setPeople(List<Astro> people) {
        this.people = people;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
