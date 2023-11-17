package org.example.demo.json.astro;

public class Astro {
    private String name, craft;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setCraft(String craft) {
        this.craft = craft;
    }

    String getCraft() {
        return this.craft;
    }

    @Override
    public String toString() {
        return "Astro{" +
                "name='" + name + '\'' +
                ", craft='" + craft + '\'' +
                '}';
    }
}
