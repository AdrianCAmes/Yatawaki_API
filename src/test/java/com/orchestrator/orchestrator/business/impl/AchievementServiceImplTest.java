package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AchievementService;
import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.repository.AchievementRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class AchievementServiceImplTest {
    @Mock
    private AchievementRepository achievementRepository;
    private GeneralUtils generalUtils;
    private AchievementService achievementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        achievementService = new AchievementServiceImpl(achievementRepository, generalUtils);
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Achievement")
    void create() {
        Achievement achievement = new Achievement();
        achievement.setDescription("My description");
        achievement.setUnlockerType("Rank");
        achievement.setUnlockerValue(4);
        achievement.setRareness("Gold");

        Achievement createdAchievement = new Achievement();
        createdAchievement.setIdUnlockable(1L);

        when(achievementRepository.save(any())).thenReturn( createdAchievement);

        Achievement achievementCreated = achievementService.create(achievement);

        assertEquals(achievementCreated.getIdUnlockable(), createdAchievement.getIdUnlockable());

    }


    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() {
        String expectedMessage = "Body should not contain id";
        Achievement achievement = new Achievement();
        achievement.setDescription("My description");
        achievement.setUnlockerType("Rank");
        achievement.setUnlockerValue(4);
        achievement.setRareness("Gold");
        achievement.setIdUnlockable(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Achievement createdAchievement = achievementService.create(achievement);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);

    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns Achievement")
    void findById() {
        Achievement achievement = new Achievement();
        achievement.setDescription("My description");
        achievement.setUnlockerType("Rank");
        achievement.setUnlockerValue(4);
        achievement.setRareness("Gold");

        when(achievementRepository.findById(1L)).thenReturn(Optional.ofNullable(achievement));

        final Achievement result = achievementService.findById(1L);

        assertEquals(achievement.toString(), achievement.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(achievementService.findById(1L));
    }

    @Test
    @DisplayName("When findAll Then Returns Achievement List")
    void findAll() {
        Achievement achievement = new Achievement();
        achievement.setDescription("My description");
        achievement.setUnlockerType("Rank");
        achievement.setUnlockerValue(4);
        achievement.setRareness("Gold");

        Achievement achievement2 = new Achievement();
        achievement2.setDescription("My description 2");
        achievement2.setUnlockerType("Rank 2");
        achievement2.setUnlockerValue(2);
        achievement2.setRareness("Silver");

        List<Achievement> achievementList = new ArrayList<>();
        achievementList.add(achievement);
        achievementList.add(achievement2);

        when(achievementRepository.findAll()).thenReturn( achievementList);

        List<Achievement> achievementList2 = achievementService.findAll();

        assertEquals(achievementList2.size(), 2);

    }


    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Achievement")
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
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Achievement retrievedAchievement = new Achievement();
        retrievedAchievement.setIdUnlockable(4L);
        retrievedAchievement.setDescription("Description");
        retrievedAchievement.setRareness("Gold");

        Throwable exception = Assertions.catchThrowable( () -> {
            Achievement createdAchievement = achievementService.update(retrievedAchievement);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";
        Achievement retrievedAchievement = new Achievement();
        retrievedAchievement.setIdUnlockable(4L);
        retrievedAchievement.setDescription("Description");
        retrievedAchievement.setRareness("Gold");

        Throwable exception = Assertions.catchThrowable( () -> {
            Achievement createdAchievement = achievementService.change(retrievedAchievement);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Achievement")
    void change() throws IllegalAccessException {
        Achievement achievementToChange = new Achievement();
        achievementToChange.setDescription("New Description");
        achievementToChange.setUnlockerType("Rank");
        achievementToChange.setUnlockerValue(4);

        Achievement retrievedAchievement = new Achievement();
        retrievedAchievement.setIdUnlockable(4L);
        retrievedAchievement.setDescription("Description");
        retrievedAchievement.setRareness("Gold");

        when(achievementRepository.findById(any())).thenReturn(Optional.of(retrievedAchievement));
        when(achievementRepository.save(any())).thenReturn(retrievedAchievement);

        final Achievement result = achievementService.change(achievementToChange);

        assertEquals(retrievedAchievement.toString(), result.toString());
    }



    @Test
    @DisplayName("When removeById With Valid Id Then Returns Achievement")
    void removeById(){
        Achievement retrievedAchievement = new Achievement();
        retrievedAchievement.setIdUnlockable(4L);
        retrievedAchievement.setDescription("Description");
        retrievedAchievement.setRareness("Gold");

        when(achievementRepository.findById(4L)).thenReturn(Optional.of(retrievedAchievement));

        achievementService.removeById(4L);
        verify(achievementRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Achievement createdAchievement = achievementService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

}