package org.example.demo.dao;

import org.example.demo.entities.Officer;
import org.example.demo.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JdbcOfficerDAOTest {
    @Autowired
    private JdbcOfficerDAO dao;

    @Test
    public void save() {
        Officer officer = new Officer(Rank.ENSIGN, "Wesley", "Crusher");
        officer = dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByIdThatExists() {
        Optional<Officer> officer = dao.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    public void findByIdThatDoesNotExist() {
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() {
        assertEquals(3L, dao.count());
    }

    @Test
    public void findAll() {
        List<String> dbNames = dao.findAll().stream()
                .map(Officer::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames,
                containsInAnyOrder(
                        "Kirk", "Sisko", "Archer"));
    }

    @Test
    public void delete() {
        IntStream.rangeClosed(1, 3)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
        assertEquals(0L, dao.count());
    }

    @Test
    public void existsById() {
        IntStream.rangeClosed(1, 3)
                .forEach(id -> assertTrue(dao.existsById(id)));
    }
}
