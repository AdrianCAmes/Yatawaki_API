package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Rank findByName(String name);
}
