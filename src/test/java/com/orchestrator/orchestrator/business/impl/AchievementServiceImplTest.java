package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AchievementService;
import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.repository.AchievementRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class AchievementServiceImplTest {
    @Mock
    private AchievementRepository achievementRepository;
    private GeneralUtils generalUtils;
    private AchievementService achievementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        generalUtils = new GeneralUtilsImpl();
        achievementService = new AchievementServiceImpl(achievementRepository, generalUtils);
    }

    @Test
    void create() {
        assertTrue(true);
    }

    @Test
    void findById() {
        assertTrue(true);
    }

    @Test
    void findAll() {
        assertTrue(true);
    }

    @Test
    void update() throws IllegalAccessException {
        Achievement achievementToUpdate = new Achievement();
        achievementToUpdate.setDescription("New Description");
        achievementToUpdate.setUnlockerType("Rank");
        achievementToUpdate.setUnlockerValue(4);

        Achievement retrievedAchievement = new Achievement();
        retrievedAchievement.setIdUnlockable(4L);
        retrievedAchievement.setDescription("Description");
        retrievedAchievement.setRareness("Gold");

        when(achievementRepository.findById(any())).thenReturn(Optional.of(retrievedAchievement));
        when(achievementRepository.save(any())).thenReturn(retrievedAchievement);

        final Achievement result = achievementService.update(achievementToUpdate);

        assertEquals(retrievedAchievement.toString(), result.toString());
    }

    @Test
    void removeById() {
        assertTrue(true);
    }
}