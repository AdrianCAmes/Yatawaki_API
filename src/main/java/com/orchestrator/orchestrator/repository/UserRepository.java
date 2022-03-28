package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String QUERY_FIND_BY_IDENTIFIER = "SELECT u FROM User u WHERE u.nickname = :uniqueIdentifier or u.mail = :uniqueIdentifier and u.status = 1";
    String QUERY_FIND_BY_MAIL = "SELECT u FROM User u WHERE u.mail = :uniqueIdentifier and u.status = 1";
    String QUERY_FIND_BY_NICKNAME = "SELECT u FROM User u WHERE u.nickname = :uniqueIdentifier and u.status = 1";

    @Query(value = QUERY_FIND_BY_MAIL)
    Optional<User> findByEmail(@Param("uniqueIdentifier") String uniqueIdentifier);

    @Query(value = QUERY_FIND_BY_NICKNAME)
    Optional<User> findByNickname(@Param("uniqueIdentifier") String uniqueIdentifier);

    @Query(value = QUERY_FIND_BY_IDENTIFIER)
    Optional<User> findByUniqueIdentifier(@Param("uniqueIdentifier") String uniqueIdentifier);
}
