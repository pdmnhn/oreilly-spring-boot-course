package org.example.demo.dao;

import org.example.demo.entities.Officer;
import org.example.demo.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
    List<Officer> findByRank(Rank rank);
    List<Officer> findAllByRankAndFirstNameContaining(Rank rank, String firstName);
}
