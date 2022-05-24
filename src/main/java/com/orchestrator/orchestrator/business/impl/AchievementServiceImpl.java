package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AchievementService;
import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.repository.AchievementRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    // Self repository
    private final AchievementRepository achievementRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Achievement create(Achievement achievement) {
        if (achievement.getIdUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return achievementRepository.save(achievement);
    }

    @Override
    public Achievement findById(Long id) {
        return achievementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Achievement> findAll() {
        return achievementRepository.findAll();
    }

    @Override
    public Achievement change(Achievement achievement) {
        Achievement achievementToChange = findById(achievement.getIdUnlockable());
        if (achievementToChange != null) {
            return achievementRepository.save(achievement);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Achievement update(Achievement achievement) throws IllegalAccessException {
        Achievement achievementToUpdate = findById(achievement.getIdUnlockable());
        if (achievementToUpdate != null) {
            generalUtils.mapFields(achievement, achievementToUpdate);
            return achievementRepository.save(achievementToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Achievement removeById(Long id) {
        Achievement achievementToDelete = findById(id);
        if (achievementToDelete != null) {
            achievementRepository.deleteById(id);
            return achievementToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
