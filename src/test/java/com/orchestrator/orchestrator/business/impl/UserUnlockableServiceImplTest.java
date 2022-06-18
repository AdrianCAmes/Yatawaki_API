package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserUnlockableService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.user.request.UserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserProfileResponseDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableTradeRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.response.UserUnlockableFilteredResponseDto;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.repository.UserRepository;
import com.orchestrator.orchestrator.repository.UserUnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.UserUnlockableStatusConstants;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserUnlockableServiceImplTest {

    @Mock
    private UserUnlockableRepository userUnlockableRepository;

    private GeneralUtils generalUtils;

    @Mock
    private UserUnlockableUtils userUnlockableUtils = mock(UserUnlockableUtils.class);

    @Mock
    private UnlockableRepository unlockableRepository;

    @Mock
    private UserRepository userRepository;
    private UserUnlockableService userUnlockableService;

    @Mock
    private UserUnlockableService userUnlockableServiceMock = mock(UserUnlockableServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        userUnlockableService = new UserUnlockableServiceImpl(userUnlockableRepository, generalUtils, userUnlockableUtils, unlockableRepository, userRepository) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns UserUnlockable")
    void create() {
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);

        UserUnlockable createdUserUnlockable = new UserUnlockable();
        createdUserUnlockable.setIdUserUnlockable(1L);

        when(userUnlockableRepository.save(any())).thenReturn(createdUserUnlockable);

        UserUnlockable result = userUnlockableService.create(userUnlockable);

        assertEquals(result.getIdUserUnlockable(), createdUserUnlockable.getIdUserUnlockable());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);
        userUnlockable.setIdUserUnlockable(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserUnlockable result = userUnlockableService.create(userUnlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns UserUnlockable")
    void findById() {
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);

        when(userUnlockableRepository.findById(1L)).thenReturn(Optional.ofNullable(userUnlockable));

        final UserUnlockable result = userUnlockableService.findById(1L);

        assertEquals(result.getStatus(), userUnlockable.getStatus());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(userUnlockableService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns UserUnlockable List")
    void findAll() {
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);

        UserUnlockable userUnlockable2 = new UserUnlockable();
        userUnlockable.setStatus(1);

        List<UserUnlockable> userUnlockableList = new ArrayList<>();
        userUnlockableList.add(userUnlockable);
        userUnlockableList.add(userUnlockable2);

        when(userUnlockableRepository.findAll()).thenReturn( userUnlockableList);

        List<UserUnlockable> result = userUnlockableService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns UserUnlockable")
    void update() throws IllegalAccessException {
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);

        UserUnlockable retrievedUserUnlockable = new UserUnlockable();
        retrievedUserUnlockable.setIdUserUnlockable(4L);
        userUnlockable.setStatus(2);

        when(userUnlockableRepository.findById(any())).thenReturn(Optional.of(retrievedUserUnlockable));
        when(userUnlockableRepository.save(any())).thenReturn(retrievedUserUnlockable);

        final UserUnlockable result = userUnlockableService.update(userUnlockable);

        assertEquals(retrievedUserUnlockable.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserUnlockable retrievedUserUnlockable = new UserUnlockable();
        retrievedUserUnlockable.setIdUserUnlockable(4L);
        retrievedUserUnlockable.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserUnlockable result = userUnlockableService.update(retrievedUserUnlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns UserUnlockable")
    void change() throws IllegalAccessException {
        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);

        UserUnlockable retrievedUserUnlockable = new UserUnlockable();
        retrievedUserUnlockable.setIdUserUnlockable(4L);
        retrievedUserUnlockable.setStatus(2);

        when(userUnlockableRepository.findById(any())).thenReturn(Optional.of(retrievedUserUnlockable));
        when(userUnlockableRepository.save(any())).thenReturn(retrievedUserUnlockable);

        final UserUnlockable result = userUnlockableService.change(userUnlockable);

        assertEquals(retrievedUserUnlockable.getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserUnlockable retrievedUserUnlockable = new UserUnlockable();
        retrievedUserUnlockable.setIdUserUnlockable(4L);
        retrievedUserUnlockable.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserUnlockable result = userUnlockableService.change(retrievedUserUnlockable);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns UserUnlockable")
    void removeById(){
        UserUnlockable retrievedUserUnlockable = new UserUnlockable();
        retrievedUserUnlockable.setIdUserUnlockable(4L);
        retrievedUserUnlockable.setStatus(1);

        when(userUnlockableRepository.findById(4L)).thenReturn(Optional.of(retrievedUserUnlockable));

        userUnlockableService.removeById(4L);
        verify(userUnlockableRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            UserUnlockable result = userUnlockableService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns UserUnlockable List")
    void getPossibleStatus() {
        UserUnlockableStatusConstants userUnlockableStatusConstants = UserUnlockableStatusConstants.DELETED;
        UserUnlockableStatusConstants userUnlockableStatusConstants2 = UserUnlockableStatusConstants.ACTIVE;
        UserUnlockableStatusConstants userUnlockableStatusConstants3 = UserUnlockableStatusConstants.IN_USE;

        List<UserUnlockableStatusConstants> userUnlockableStatusConstantsList = new ArrayList<>();
        userUnlockableStatusConstantsList.add(userUnlockableStatusConstants);
        userUnlockableStatusConstantsList.add(userUnlockableStatusConstants2);
        userUnlockableStatusConstantsList.add(userUnlockableStatusConstants3);

        when(userUnlockableServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(UserUnlockableStatusConstants.values()).collect(Collectors.toList()));

        List<UserUnlockableStatusConstants> result = userUnlockableService.getPossibleStatus();

        assertEquals(result, userUnlockableStatusConstantsList);
    }

    //Integration Tests
    @Test
    @DisplayName("When findFilteredUnlockablesByUserId With Valid User Id Then Returns UserUnlockableFilteredResponseDto")
    void findFilteredUnlockablesByUserId(){
        List<Unlockable> achievements = new ArrayList<>();
        achievements.add(new Achievement());
        achievements.add(new Achievement());

        when(userUnlockableRepository.findAchievementsByUserId(any())).thenReturn(achievements);

        List<Unlockable> symphonies = new ArrayList<>();
        symphonies.add(new Symphony());
        symphonies.add(new Symphony());

        when(userUnlockableRepository.findSymphoniesByUserId(any())).thenReturn(symphonies);

        List<Unlockable> avatars = new ArrayList<>();
        avatars.add(new Avatar());
        avatars.add(new Avatar());

        when(userUnlockableRepository.findAvatarsByUserId(any())).thenReturn(avatars);

        UserUnlockableFilteredResponseDto userUnlockableFilteredResponseDto = new UserUnlockableFilteredResponseDto();
        userUnlockableFilteredResponseDto.setAchievements(achievements);
        userUnlockableFilteredResponseDto.setSymphonies(symphonies);
        userUnlockableFilteredResponseDto.setAvatars(avatars);

        final UserUnlockableFilteredResponseDto result = userUnlockableService.findFilteredUnlockablesByUserId(1L);

        assertEquals(result.toString(), userUnlockableFilteredResponseDto.toString());
    }

    @Test
    @DisplayName("When findAvatarsByUserId With Valid User Id Then Returns Unlockable List")
    void findAvatarsByUserId(){
        List<Unlockable> avatars = new ArrayList<>();
        avatars.add(new Symphony());
        avatars.add(new Symphony());

        when(userUnlockableRepository.findAvatarsByUserId(any())).thenReturn(avatars);

        final List<Unlockable> result = userUnlockableService.findAvatarsByUserId(1L);

        assertEquals(result.size(), avatars.size());
    }

    @Test
    @DisplayName("When findMarketUnlockableByUserId With Valid User Id Then Returns UserUnlockableFilteredResponseDto")
    void findMarketUnlockableByUserId(){
        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(new Achievement());
        unlockableList.add(new Symphony());
        unlockableList.add(new Avatar());
        unlockableList.get(0).setIdUnlockable(1L);
        unlockableList.get(1).setIdUnlockable(2L);
        unlockableList.get(2).setIdUnlockable(3L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(unlockableList);

        List<Unlockable> marketUnlockables = new ArrayList<>();
        marketUnlockables.add(new Achievement());
        marketUnlockables.add(new Symphony());
        marketUnlockables.add(new Avatar());
        marketUnlockables.get(0).setIdUnlockable(4L);
        marketUnlockables.get(1).setIdUnlockable(5L);
        marketUnlockables.get(2).setIdUnlockable(6L);

        when(unlockableRepository.findMarketUnlockables()).thenReturn(marketUnlockables);

        List<Unlockable> achievements = new ArrayList<>();
        achievements.add(new Achievement());

        List<Unlockable> symphonies = new ArrayList<>();
        symphonies.add(new Symphony());

        List<Unlockable> avatars = new ArrayList<>();
        avatars.add(new Avatar());

        UserUnlockableFilteredResponseDto userUnlockableFilteredResponseDto = new UserUnlockableFilteredResponseDto();
        userUnlockableFilteredResponseDto.setAchievements(achievements);
        userUnlockableFilteredResponseDto.setSymphonies(symphonies);
        userUnlockableFilteredResponseDto.setAvatars(avatars);

        final UserUnlockableFilteredResponseDto result = userUnlockableService.findMarketUnlockablesByUserId(1L);

        assertEquals(result.toString(), userUnlockableFilteredResponseDto.toString());
    }


    @Test
    @DisplayName("When findMarketUnlockableByUserId With Valid User Id And An Unlockable Already Owned Then Returns UserUnlockableFilteredResponseDto")
    void findMarketUnlockableByUserIdUnlockableAlreadyOwned(){
        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(new Achievement());
        unlockableList.add(new Symphony());
        unlockableList.add(new Avatar());
        unlockableList.get(0).setIdUnlockable(1L);
        unlockableList.get(1).setIdUnlockable(2L);
        unlockableList.get(2).setIdUnlockable(3L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(unlockableList);

        List<Unlockable> marketUnlockables = new ArrayList<>();
        marketUnlockables.add(new Achievement());
        marketUnlockables.add(new Symphony());
        marketUnlockables.add(new Avatar());
        marketUnlockables.get(0).setIdUnlockable(2L);
        marketUnlockables.get(1).setIdUnlockable(5L);
        marketUnlockables.get(2).setIdUnlockable(6L);


        when(unlockableRepository.findMarketUnlockables()).thenReturn(marketUnlockables);

        List<Unlockable> achievements = new ArrayList<>();

        List<Unlockable> symphonies = new ArrayList<>();
        symphonies.add(new Symphony());

        List<Unlockable> avatars = new ArrayList<>();
        avatars.add(new Avatar());

        UserUnlockableFilteredResponseDto userUnlockableFilteredResponseDto = new UserUnlockableFilteredResponseDto();
        userUnlockableFilteredResponseDto.setAchievements(achievements);
        userUnlockableFilteredResponseDto.setSymphonies(symphonies);
        userUnlockableFilteredResponseDto.setAvatars(avatars);

        final UserUnlockableFilteredResponseDto result = userUnlockableService.findMarketUnlockablesByUserId(1L);

        assertEquals(result.toString(), userUnlockableFilteredResponseDto.toString());
    }


    @Test
    @DisplayName("When unlockObjectsByUnlocker With Valid User Id And An Unlockable Already Owned Then Returns Unlockable List")
    void unlockObjectsByUnlockerUnlockableAlreadyOwned() throws IllegalAccessException {
        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(new Achievement());
        unlockableList.add(new Symphony());
        unlockableList.add(new Avatar());
        unlockableList.get(0).setIdUnlockable(1L);
        unlockableList.get(1).setIdUnlockable(2L);
        unlockableList.get(2).setIdUnlockable(3L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(unlockableList);

        List<Unlockable> objectsToUnlock = new ArrayList<>();
        objectsToUnlock.add(new Achievement());
        objectsToUnlock.add(new Symphony());
        objectsToUnlock.add(new Avatar());
        objectsToUnlock.get(0).setIdUnlockable(1L);
        objectsToUnlock.get(1).setIdUnlockable(5L);
        objectsToUnlock.get(2).setIdUnlockable(6L);

        UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto = new UserUnlockableUnlockRequestDto();
        userUnlockableUnlockRequestDto.setIdUser(1L);

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(any(), any())).thenReturn(objectsToUnlock);

        for (Unlockable unlockable : objectsToUnlock) {
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
            userUnlockableCreateRequestDto.setIdUser(userUnlockableUnlockRequestDto.getIdUser());

            when(userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto)).thenReturn(new UserUnlockable());
        }

        final List<Unlockable> result = userUnlockableService.unlockObjectsByUnlocker(userUnlockableUnlockRequestDto);

        objectsToUnlock.remove(0);
        System.out.println(result);
        System.out.println(objectsToUnlock);
        assertEquals(result.toString(), objectsToUnlock.toString());
    }


    @Test
    @DisplayName("When unlockObjectsByUnlocker With Valid User Id Then Returns Unlockable List")
    void unlockObjectsByUnlocker() throws IllegalAccessException {
        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(new Achievement());
        unlockableList.add(new Symphony());
        unlockableList.add(new Avatar());
        unlockableList.get(0).setIdUnlockable(1L);
        unlockableList.get(1).setIdUnlockable(2L);
        unlockableList.get(2).setIdUnlockable(3L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(unlockableList);

        List<Unlockable> objectsToUnlock = new ArrayList<>();
        objectsToUnlock.add(new Achievement());
        objectsToUnlock.add(new Symphony());
        objectsToUnlock.add(new Avatar());
        objectsToUnlock.get(0).setIdUnlockable(4L);
        objectsToUnlock.get(1).setIdUnlockable(5L);
        objectsToUnlock.get(2).setIdUnlockable(6L);

        UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto = new UserUnlockableUnlockRequestDto();
        userUnlockableUnlockRequestDto.setIdUser(1L);

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(any(), any())).thenReturn(objectsToUnlock);


        for(Unlockable unlockable : objectsToUnlock){
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
            userUnlockableCreateRequestDto.setIdUser(userUnlockableUnlockRequestDto.getIdUser());

            when(userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto)).thenReturn(new UserUnlockable());
        }

        final List<Unlockable> result = userUnlockableService.unlockObjectsByUnlocker(userUnlockableUnlockRequestDto);

        assertEquals(result.toString(), objectsToUnlock.toString());
    }


    @Test
    @DisplayName("When unlockObjectsByUnlocker With Valid User Id And No Object To Unlock Then Returns Unlockable List")
    void unlockObjectsByUnlockerNoObjectsToUnlock() throws IllegalAccessException {
        List<Unlockable> unlockableList = new ArrayList<>();
        unlockableList.add(new Achievement());
        unlockableList.add(new Symphony());
        unlockableList.add(new Avatar());
        unlockableList.get(0).setIdUnlockable(1L);
        unlockableList.get(1).setIdUnlockable(2L);
        unlockableList.get(2).setIdUnlockable(3L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(unlockableList);

        List<Unlockable> objectsToUnlock = new ArrayList<>();

        UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto = new UserUnlockableUnlockRequestDto();
        userUnlockableUnlockRequestDto.setIdUser(1L);

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(any(), any())).thenReturn(objectsToUnlock);

        final List<Unlockable> result = userUnlockableService.unlockObjectsByUnlocker(userUnlockableUnlockRequestDto);

        assertEquals(result.toString(), objectsToUnlock.toString());
    }


    @Test
    @DisplayName("When tradeObject With Valid Attributes Then Returns Unlockable")
    void tradeObject() throws IllegalAccessException {
        Unlockable unlockableToTrade = new Unlockable();
        unlockableToTrade.setCoinsCost(100);
        unlockableToTrade.setIdUnlockable(1L);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(unlockableToTrade));

        User user = new User();
        user.setCoinsOwned(1000);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        List<Unlockable> ownedUnlockables = new ArrayList<>();
        ownedUnlockables.add(new Unlockable());
        ownedUnlockables.get(0).setIdUnlockable(2L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(ownedUnlockables);

        UserUnlockableTradeRequestDto userUnlockableTradeRequestDto = new UserUnlockableTradeRequestDto();
        userUnlockableTradeRequestDto.setIdUser(1L);
        userUnlockableTradeRequestDto.setIdUnlockable(1L);

        UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
        userUnlockableCreateRequestDto.setIdUnlockable(1L);
        userUnlockableCreateRequestDto.setIdUser(1L);

        UserUnlockable userUnlockable = new UserUnlockable();
        userUnlockable.setStatus(1);
        userUnlockable.setUser(user);
        userUnlockable.setUnlockable(unlockableToTrade);
        when(userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto)).thenReturn(userUnlockable);

        final Unlockable result = userUnlockableService.tradeObject(userUnlockableTradeRequestDto);

        assertEquals(result.toString(), unlockableToTrade.toString());
    }

    @Test
    @DisplayName("When tradeObject With Invalid Unlockable Id Then Returns NoSuchElementException")
    void tradeObjectInvalidUnlockableId() throws IllegalAccessException {
        String expectedMessage = "Unlockable not found in database";
        Unlockable unlockableToTrade = new Unlockable();
        unlockableToTrade.setCoinsCost(100);
        unlockableToTrade.setIdUnlockable(1L);

        Throwable exception = Assertions.catchThrowable(() -> {
            Unlockable result = userUnlockableService.tradeObject(new UserUnlockableTradeRequestDto());
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When tradeObject With Valid Unlockable Id And Invalid User Id Then Returns NoSuchElementException")
    void tradeObjectInvalidId() throws IllegalAccessException {
        String expectedMessage = "User not found in database";
        Unlockable unlockableToTrade = new Unlockable();
        unlockableToTrade.setCoinsCost(100);
        unlockableToTrade.setIdUnlockable(1L);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(unlockableToTrade));

        Throwable exception = Assertions.catchThrowable(() -> {
            Unlockable result = userUnlockableService.tradeObject(new UserUnlockableTradeRequestDto());
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When tradeObject With Valid Unlockable Id And Valid User Id And Coins Cost Greater Than Coins Owned Then Returns UnsupportedOperationException")
    void tradeObjectCoinsCostGreaterThanCoinsOwned() throws IllegalAccessException {
        String expectedMessage = "User does not have sufficient coins";
        Unlockable unlockableToTrade = new Unlockable();
        unlockableToTrade.setCoinsCost(100);
        unlockableToTrade.setIdUnlockable(1L);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(unlockableToTrade));

        User user = new User();
        user.setCoinsOwned(10);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        Throwable exception = Assertions.catchThrowable(() -> {
            Unlockable result = userUnlockableService.tradeObject(new UserUnlockableTradeRequestDto());
        });

        Assertions.assertThat(exception).isInstanceOf(UnsupportedOperationException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When tradeObject With Valid Unlockable Id And Valid User Id And Coins Cost Smaller Than Coins Owned And Object Already Unlocked Then Returns Unlockable")
    void tradeObjectObjectAlreadyUnlocked() throws IllegalAccessException {
        String expectedMessage = "Object already unlocked for user";
        Unlockable unlockableToTrade = new Unlockable();
        unlockableToTrade.setCoinsCost(100);
        unlockableToTrade.setIdUnlockable(1L);

        when(unlockableRepository.findById(any())).thenReturn(Optional.of(unlockableToTrade));

        User user = new User();
        user.setCoinsOwned(1000);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        List<Unlockable> ownedUnlockables = new ArrayList<>();
        ownedUnlockables.add(new Unlockable());
        ownedUnlockables.get(0).setIdUnlockable(1L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(ownedUnlockables);

        Throwable exception = Assertions.catchThrowable(() -> {
            Unlockable result = userUnlockableService.tradeObject(new UserUnlockableTradeRequestDto());
        });

        Assertions.assertThat(exception).isInstanceOf(UnsupportedOperationException.class).hasMessage(expectedMessage);
    }
}
