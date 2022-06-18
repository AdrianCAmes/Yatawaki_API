package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AvatarService;
import com.orchestrator.orchestrator.model.Avatar;
import com.orchestrator.orchestrator.repository.AvatarRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AvatarServiceImplTest {
    @Mock
    private AvatarRepository avatarRepository;

    private GeneralUtils generalUtils;
    private AvatarService avatarService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        avatarService = new AvatarServiceImpl(avatarRepository, generalUtils);
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Avatar")
    void create() {
        Avatar avatar = new Avatar();
        avatar.setName("My name");
        avatar.setDescription("My description");

        Avatar createdAvatar = new Avatar();
        createdAvatar.setIdUnlockable(1L);

        when(avatarRepository.save(any())).thenReturn(createdAvatar);

        Avatar result = avatarService.create(avatar);

        assertEquals(result.getIdUnlockable(), createdAvatar.getIdUnlockable());

    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() {
        String expectedMessage = "Body should not contain id";
        Avatar avatar = new Avatar();
        avatar.setDescription("My name");
        avatar.setDescription("My description");
        avatar.setIdUnlockable(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Avatar result = avatarService.create(avatar);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns Avatar")
    void findById() {
        Avatar avatar = new Avatar();
        avatar.setName("My name");
        avatar.setDescription("My description");

        when(avatarRepository.findById(1L)).thenReturn(Optional.ofNullable(avatar));

        final Avatar result = avatarService.findById(1L);

        assertEquals(result.toString(), avatar.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(avatarService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns Avatar List")
    void findAll() {
        Avatar avatar = new Avatar();
        avatar.setName("My name");
        avatar.setDescription("My description");

        Avatar avatar2 = new Avatar();
        avatar2.setName("My name 2");
        avatar2.setDescription("My description 2");


        List<Avatar> avatarList = new ArrayList<>();
        avatarList.add(avatar);
        avatarList.add(avatar2);

        when(avatarRepository.findAll()).thenReturn( avatarList);

        List<Avatar> result = avatarService.findAll();

        assertEquals(result.size(), 2);

    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Avatar")
    void update() throws IllegalAccessException {
        Avatar avatar = new Avatar();
        avatar.setName("New Name");
        avatar.setDescription("New Description");

        Avatar retrievedAvatar = new Avatar();
        retrievedAvatar.setIdUnlockable(4L);
        retrievedAvatar.setDescription("Description");

        when(avatarRepository.findById(any())).thenReturn(Optional.of(retrievedAvatar));
        when(avatarRepository.save(any())).thenReturn(retrievedAvatar);

        final Avatar result = avatarService.update(avatar);

        assertEquals(retrievedAvatar.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Avatar retrievedAvatar = new Avatar();
        retrievedAvatar.setIdUnlockable(4L);
        retrievedAvatar.setDescription("Description");

        Throwable exception = Assertions.catchThrowable( () -> {
            Avatar createdAvatar = avatarService.update(retrievedAvatar);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Avatar")
    void change() throws IllegalAccessException {
        Avatar avatar = new Avatar();
        avatar.setName("New Name");
        avatar.setDescription("New Description");

        Avatar retrievedAvatar = new Avatar();
        retrievedAvatar.setIdUnlockable(4L);
        retrievedAvatar.setDescription("Description");

        when(avatarRepository.findById(any())).thenReturn(Optional.of(retrievedAvatar));
        when(avatarRepository.save(any())).thenReturn(retrievedAvatar);

        final Avatar result = avatarService.change(avatar);

        assertEquals(retrievedAvatar.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Avatar retrievedAvatar = new Avatar();
        retrievedAvatar.setIdUnlockable(4L);
        retrievedAvatar.setDescription("Description");

        Throwable exception = Assertions.catchThrowable( () -> {
            Avatar createdAvatar = avatarService.change(retrievedAvatar);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns Avatar")
    void removeById(){
        Avatar retrievedAvatar = new Avatar();
        retrievedAvatar.setIdUnlockable(4L);
        retrievedAvatar.setDescription("Description");

        when(avatarRepository.findById(4L)).thenReturn(Optional.of(retrievedAvatar));

        avatarService.removeById(4L);
        verify(avatarRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            Avatar createdAchievement = avatarService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }
}
