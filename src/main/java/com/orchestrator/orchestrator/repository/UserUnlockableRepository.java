package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserUnlockable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserUnlockableRepository extends JpaRepository<UserUnlockable, Long> {
    String QUERY_TO_SELECT_SYMPHONIES = "SELECT u.unlockable FROM UserUnlockable u JOIN Symphony s ON s.idUnlockable = u.unlockable.idUnlockable WHERE u.user.idUser = :idUser and u.status = 1 ";
    String QUERY_TO_SELECT_UNLOCKABLES = "SELECT u.unlockable FROM UserUnlockable u WHERE u.user.idUser = :idUser and u.status = 1 ";
    String QUERY_TO_SELECT_IN_USE_AVATAR = "SELECT u.unlockable FROM UserUnlockable u JOIN Avatar a ON a.idUnlockable = u.unlockable.idUnlockable WHERE u.user.idUser = :idUser and u.status = 2 ";

    @Query(value = QUERY_TO_SELECT_SYMPHONIES)
    List<Unlockable> findSymphoniesByUser(Long idUser);

    @Query(value = QUERY_TO_SELECT_UNLOCKABLES)
    List<Unlockable> findUnlockablesByUser(Long idUser);

    @Query(value = QUERY_TO_SELECT_IN_USE_AVATAR)
    Optional<Unlockable> findInUseAvatarByUser(Long idUser);
}
