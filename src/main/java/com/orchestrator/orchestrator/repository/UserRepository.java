package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String QUERY_TO_AUTHENTICATE = "SELECT u FROM User u WHERE u.nickname = :uniqueIdentifier or u.mail = :uniqueIdentifier and u.password = :password and u.status = 1";

    @Query(value = QUERY_TO_AUTHENTICATE)
    Optional<User> findByUniqueIdentifierAndPassword(String uniqueIdentifier, String password);
}
