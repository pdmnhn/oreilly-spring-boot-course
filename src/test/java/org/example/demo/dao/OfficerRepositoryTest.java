package org.example.demo.dao;

import org.example.demo.entities.Officer;
import org.example.demo.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OfficerRepositoryTest {
    @Autowired
    private OfficerRepository dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Integer> getIds() {
        return jdbcTemplate.query("select id from officers", (rs, rowNum) -> rs.getInt("id"));
    }

    @Test
    public void save() {
        Officer officer = new Officer(Rank.ENSIGN, "Wesley", "Crusher");
        officer = dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByIdThatExists() {
        getIds().forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            assertEquals(id, officer.get().getId());
        });
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
        getIds()
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
        assertEquals(0L, dao.count());
    }

    @Test
    public void existsById() {
        getIds()
                .forEach(id -> assertTrue(dao.existsById(id)));
    }

    @Test
    public void findByRank() {
        dao.findByRank(Rank.CAPTAIN).forEach(officer -> {
            assertEquals(Rank.CAPTAIN, officer.getRank());
        });
    }

    @Test
    public void findAllByRankAndFirstNameContaining() {
        dao.findAllByRankAndFirstNameContaining(Rank.CAPTAIN, "i").forEach(officer -> {
            assertAll(
                    () -> assertEquals(Rank.CAPTAIN, officer.getRank()),
                    () -> assertTrue(officer.getFirstName().contains("i"))
            );
        });
    }
}
