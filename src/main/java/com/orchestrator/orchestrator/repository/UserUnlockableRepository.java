package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.UserUnlockable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUnlockableRepository extends JpaRepository<UserUnlockable, Long> {
}
