package org.example.demo.dao;

import org.example.demo.entities.Officer;
import org.example.demo.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOfficerDAO implements OfficerDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private static final RowMapper<Officer> officerRowMapper =
            (ResultSet rs, int rowNum) -> new Officer(rs.getInt("id"),
                    Rank.valueOf(rs.getString("rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name"));

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Officer save(Officer officer) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("rank", officer.getRank().toString());
        parameters.put("first_name", officer.getFirstName());
        parameters.put("last_name", officer.getLastName());
        Integer id = (Integer) this.jdbcInsert.executeAndReturnKey(parameters);
        officer.setId(id);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        if (!this.existsById(id)) return Optional.empty();
        return Optional.of(this.jdbcTemplate.queryForObject("select * from officers where id=?",
                officerRowMapper,
                id));
    }

    @Override
    public List<Officer> findAll() {
        return this.jdbcTemplate.query("select * from officers",
                officerRowMapper);
    }

    @Override
    public long count() {
        return this.jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        this.jdbcTemplate.update("delete from officers where id=?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select exists(select 1 from officers where id=?)", Boolean.class, id);
    }
}
