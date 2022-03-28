package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRankRepository extends JpaRepository<UserRank, Long> {
    String QUERY_FIND_BY_USER = "SELECT u FROM UserRank u WHERE u.user.idUser = :idUser and u.status = 1";

    @Query(value = QUERY_FIND_BY_USER)
    Optional<UserRank> findLastActiveByUser(Long idUser);
}
