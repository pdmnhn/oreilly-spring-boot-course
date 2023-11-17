package org.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "officers")
public class Officer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Rank rank;
    private String firstName;
    private String lastName;

    public Officer() {}

    public Officer(Rank rank, String firstName, String lastName) {
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Officer(Integer id, Rank rank, String firstName, String lastName) {
        this.id = id;
        this.rank = rank;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Officer officer = (Officer) o;
        return Objects.equals(id, officer.id) && rank == officer.rank && Objects.equals(firstName, officer.firstName) && Objects.equals(lastName, officer.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
