package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.user.response.UserProfileResponseDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDtoTest;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.*;
import com.orchestrator.orchestrator.utils.constants.*;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GeneralUtils generalUtils = mock(GeneralUtilsImpl.class);
    @Mock
    private UserStatisticsUtils userStatisticsUtils = mock(UserStatisticsUtils.class);

    @Mock
    private UserRankUtils userRankUtils;

    @Mock
    private UserUnlockableUtils userUnlockableUtils;
    @Mock
    private UserStatisticsRepository userStatisticsRepository;

    @Mock
    private RankRepository rankRepository;

    @Mock
    private UserRankRepository userRankRepository;

    @Mock
    private UnlockableRepository unlockableRepository;

    @Mock
    private UserUnlockableRepository userUnlockableRepository;

    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private UserService userService;

    @Mock
    private UserService userServiceMock = mock(UserServiceImpl.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, generalUtils, userStatisticsUtils, userRankUtils, userUnlockableUtils, userStatisticsRepository, rankRepository, userRankRepository, unlockableRepository, userUnlockableRepository, passwordEncoder) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns User")
    void create() {
        User user = new User();
        user.setNickname("My name");

        User createdUser = new User();
        createdUser.setIdUser(1L);

        when(userRepository.save(any())).thenReturn(createdUser);

        User result = userService.create(user);

        assertEquals(result.getIdUser(), createdUser.getIdUser());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws IllegalArgumentException {
        String expectedMessage = "Body should not contain id";
        User user = new User();
        user.setNickname("My name");
        user.setIdUser(1L);

        Throwable exception = Assertions.catchThrowable(() -> {
            User result = userService.create(user);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns User")
    void findById() {
        User user = new User();
        user.setNickname("My name");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        final User result = userService.findById(1L);

        assertEquals(result.toString(), user.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(userService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns User List")
    void findAll() {
        User user = new User();
        user.setNickname("My name");

        User user2 = new User();
        user2.setNickname("My name 2");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns User")
    void update() throws IllegalAccessException {
        User user = new User();
        user.setNickname("New Name");

        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Old Name");

        when(userRepository.findById(any())).thenReturn(Optional.of(retrievedUser));
        when(userRepository.save(any())).thenReturn(retrievedUser);

        final User result = userService.update(user);

        assertEquals(retrievedUser.toString(), result.toString());
    }


    @Test
    @DisplayName("When update With Valid Attributes And Valid Id And Not Null Password Then Returns User")
    void updateNotNullPassword() throws IllegalAccessException {
        User user = new User();
        user.setNickname("New Name");
        user.setPassword("Hola");

        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Old Name");
        retrievedUser.setPassword("Hola");

        when(userRepository.findById(any())).thenReturn(Optional.of(retrievedUser));
        when(passwordEncoder.encode(retrievedUser.getPassword())).thenReturn("#nju");
        when(userRepository.save(any())).thenReturn(retrievedUser);

        final User result = userService.update(user);

        assertEquals(retrievedUser.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Name");

        Throwable exception = Assertions.catchThrowable(() -> {
            User result = userService.update(retrievedUser);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns User")
    void change() throws IllegalAccessException {
        User user = new User();
        user.setNickname("New Name");
        user.setPassword("Hola");

        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Old Name");
        retrievedUser.setPassword("Hola");

        when(userRepository.findById(any())).thenReturn(Optional.of(retrievedUser));
        when(userRepository.save(any())).thenReturn(retrievedUser);

        final User result = userService.change(user);

        System.out.println(result);
        assertEquals(retrievedUser.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Name");

        Throwable exception = Assertions.catchThrowable(() -> {
            User result = userService.change(retrievedUser);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns User")
    void removeById() {
        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Name");

        when(userRepository.findById(4L)).thenReturn(Optional.of(retrievedUser));

        userService.removeById(4L);
        verify(userRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable(() -> {
            User result = userService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns UserStatusConstants List")
    void getPossibleStatus() {
        UserStatusConstants userStatusConstants = UserStatusConstants.DELETED;
        UserStatusConstants userStatusConstants2 = UserStatusConstants.ACTIVE;
        UserStatusConstants userStatusConstants3 = UserStatusConstants.BLOCKED;

        List<UserStatusConstants> userStatusConstantsList = new ArrayList<>();
        userStatusConstantsList.add(userStatusConstants);
        userStatusConstantsList.add(userStatusConstants2);
        userStatusConstantsList.add(userStatusConstants3);

        when(userServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(UserStatusConstants.values()).collect(Collectors.toList()));

        List<UserStatusConstants> result = userService.getPossibleStatus();

        assertEquals(result, userStatusConstantsList);
    }

    //Integration Tests

    @Test
    @DisplayName("When register With Valid Attributes And Without An Id Then Returns User")
    void register() throws IllegalAccessException {
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        UserStatistics userStatisticsToSave = new UserStatistics();
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(2);

        when(userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto)).thenReturn(userStatisticsToSave);

        when(userStatisticsRepository.save(userStatisticsToSave)).thenReturn(userStatistics);

        User user = new User();
        user.setNickname("My name");
        user.setUserStatistics(userStatistics);

        when(userRepository.save(any())).thenReturn(user);


        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(1L);

        when(rankRepository.findByLevel(NumericConstants.ONE.getValue())).thenReturn(new Rank());

        userRankCreateRequestDto.setIdRank(1L);

        UserRank userRank = new UserRank();
        userRank.setStatus(3);

        when(userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto)).thenReturn(userRank);
        when(userRankRepository.save(userRank)).thenReturn(userRank);

        List<Unlockable> baseUnlockables = new ArrayList<>();
        baseUnlockables.add(new Unlockable());
        baseUnlockables.add(new Unlockable());

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getValue(), 1)).thenReturn(baseUnlockables);

        for (Unlockable unlockable : baseUnlockables) {
            userUnlockableRepository.save(new UserUnlockable());
        }

        User result = userService.register(user);

        System.out.println(result);
        System.out.println(user);
        assertEquals(result.getUserStatistics().getStatus(), user.getUserStatistics().getStatus());
    }


    @Test
    @DisplayName("When register With Valid Attributes And Without An Id And One Unlockable Is An Instance Of Avatar Then Returns User")
    void registerOneUnlockableInstanceAvatar() throws IllegalAccessException {
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        UserStatistics userStatisticsToSave = new UserStatistics();
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(2);

        when(userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto)).thenReturn(userStatisticsToSave);

        when(userStatisticsRepository.save(userStatisticsToSave)).thenReturn(userStatistics);

        User user = new User();
        user.setNickname("My name");
        user.setUserStatistics(userStatistics);

        when(userRepository.save(any())).thenReturn(user);

        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(1L);

        when(rankRepository.findByLevel(NumericConstants.ONE.getValue())).thenReturn(new Rank());

        userRankCreateRequestDto.setIdRank(1L);

        UserRank userRank = new UserRank();
        userRank.setStatus(3);

        when(userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto)).thenReturn(userRank);
        when(userRankRepository.save(userRank)).thenReturn(userRank);

        List<Unlockable> baseUnlockables = new ArrayList<>();
        baseUnlockables.add(new Unlockable());
        baseUnlockables.add(new Avatar());

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getValue(), 1)).thenReturn(baseUnlockables);

        for (Unlockable unlockable : baseUnlockables) {
            UserUnlockable userUnlockableToSave = new UserUnlockable();

            when(userUnlockableUtils.buildDomainFromCreateRequestDto(new UserUnlockableCreateRequestDto())).thenReturn(userUnlockableToSave);
            if (unlockable instanceof Avatar) {
                userUnlockableToSave.setStatus(3);
                unlockable.setStatus(3);
            }
            when(userUnlockableRepository.save(new UserUnlockable())).thenReturn(userUnlockableToSave);
        }

        User result = userService.register(user);

        assertNull(baseUnlockables.get(0).getStatus());
        assertEquals(baseUnlockables.get(1).getStatus(), 3);
    }


    @Test
    @DisplayName("When findUserResumeByUsername With Valid Attributes Then Returns UserResume")
    void findUserResumeByUsername() throws IllegalAccessException {
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findByUniqueIdentifier("Nickname")).thenReturn(Optional.of(user));

        Rank rank = new Rank();
        rank.setLevel(5);

        UserRank lastActiveRank = new UserRank();
        lastActiveRank.setStatus(1);
        lastActiveRank.setRank(rank);
        lastActiveRank.setCurrentExperience(100);
        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(lastActiveRank));

        Unlockable avatar = new Unlockable();
        avatar.setIcon("Icon");

        when(userUnlockableRepository.findInUseAvatarByUserId(any())).thenReturn(Optional.of(avatar));

        List<Unlockable> ownedUnlockables = new ArrayList<>();
        ownedUnlockables.add(new Unlockable());
        when(userUnlockableRepository.findSymphoniesByUserId(any())).thenReturn(ownedUnlockables);

        UserResumeResponseDto userResumeResponseDto = new UserResumeResponseDto();
        userResumeResponseDto.setLevel(lastActiveRank.getRank().getLevel());
        userResumeResponseDto.setCurrentExperience(lastActiveRank.getCurrentExperience());
        userResumeResponseDto.setIcon(avatar.getIcon());
        userResumeResponseDto.setSymphonies(ownedUnlockables);

        final UserResumeResponseDto result = userService.findUserResumeByUsername("Nickname");

        assertEquals(result.toString(), userResumeResponseDto.toString());

    }

    @Test
    @DisplayName("When findUserResumeByUsername With Invalid Username Then Returns NoSuchElementException")
    void findUserResumeByUsernameInvalidUsername() throws IllegalAccessException {
        String expectedMessage = "User does not exist in database";
        User user = new User();

        Throwable exception = Assertions.catchThrowable(() -> {
            UserResumeResponseDto result = userService.findUserResumeByUsername("Hi");
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);


    }

    @Test
    @DisplayName("When findUserResumeByUsername With Valid Username And Invalid Last Active User Then Returns NoSuchElementException")
    void findUserResumeByUsernameInvalidLastActiveUser() throws IllegalAccessException {
        String expectedMessage = "Active rank not found for user";
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findByUniqueIdentifier("Nickname")).thenReturn(Optional.of(user));

        Throwable exception = Assertions.catchThrowable(() -> {
            UserResumeResponseDto result = userService.findUserResumeByUsername("Nickname");
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findUserResumeByUsername With Valid Username And Valid Last Active Rank And Invalid Avatar Then Returns NoSuchElementException")
    void findUserResumeByUsernameInvalidAvatar() throws IllegalAccessException {
        String expectedMessage = "Avatar not found for user";
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findByUniqueIdentifier("Nickname")).thenReturn(Optional.of(user));

        Rank rank = new Rank();
        rank.setLevel(5);

        UserRank lastActiveRank = new UserRank();
        lastActiveRank.setStatus(1);
        lastActiveRank.setRank(rank);
        lastActiveRank.setCurrentExperience(100);
        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(lastActiveRank));

        Throwable exception = Assertions.catchThrowable(() -> {
            UserResumeResponseDto result = userService.findUserResumeByUsername("Nickname");
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findUserResumeByUsername With Valid Username And Valid Last Active Rank And Valid Avatar And Invalid Symphonies Then Returns IndexOutOfBoundsException")
    void findUserResumeByUsernameInvalidSymphonies() throws IllegalAccessException {
        String expectedMessage = "User does not have symphonies";
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findByUniqueIdentifier("Nickname")).thenReturn(Optional.of(user));

        Rank rank = new Rank();
        rank.setLevel(5);

        UserRank lastActiveRank = new UserRank();
        lastActiveRank.setStatus(1);
        lastActiveRank.setRank(rank);
        lastActiveRank.setCurrentExperience(100);
        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(lastActiveRank));

        Unlockable avatar = new Unlockable();
        avatar.setIcon("Icon");

        when(userUnlockableRepository.findInUseAvatarByUserId(any())).thenReturn(Optional.of(avatar));

        Throwable exception = Assertions.catchThrowable(() -> {
            UserResumeResponseDto result = userService.findUserResumeByUsername("Nickname");
        });

        Assertions.assertThat(exception).isInstanceOf(IndexOutOfBoundsException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleRoles Then Returns UserRoleConstants List")
    void getPossibleRoles() {
        UserRoleConstants userRoleConstants = UserRoleConstants.ADMIN;
        UserRoleConstants userStatusConstants2 = UserRoleConstants.PLAYER;

        List<UserRoleConstants> userRoleConstantsList = new ArrayList<>();
        userRoleConstantsList.add(userRoleConstants);
        userRoleConstantsList.add(userStatusConstants2);

        List<UserRoleConstants> result = userService.getPossibleRoles();

        assertEquals(result, userRoleConstantsList);
    }

    @Test
    @DisplayName("When updateByMail With Valid Attributes And Valid Mail Then Returns User")
    void updateByMail() throws IllegalAccessException {
        User user = new User();
        user.setNickname("New Name");
        user.setMail("Mail@gmail.com");

        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Old Name");
        retrievedUser.setMail("Mail@gmail.com");

        when(userRepository.findByUniqueIdentifier(any())).thenReturn(Optional.of(retrievedUser));
        when(userRepository.save(any())).thenReturn(retrievedUser);

        final User result = userService.updateByMail(user);

        assertEquals(retrievedUser.toString(), result.toString());
    }


    @Test
    @DisplayName("When updateByMail With Valid Attributes And Valid Mail And Not Null Password Then Returns User")
    void updateByMailNotNullPassword() throws IllegalAccessException {
        User user = new User();
        user.setNickname("New Name");
        user.setPassword("Hola");
        user.setMail("Mail@gmail.com");

        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Old Name");
        retrievedUser.setPassword("Hola");
        retrievedUser.setMail("Mail@gmail.com");

        when(userRepository.findByUniqueIdentifier(any())).thenReturn(Optional.of(retrievedUser));
        when(passwordEncoder.encode(retrievedUser.getPassword())).thenReturn("#nju");
        when(userRepository.save(any())).thenReturn(retrievedUser);

        final User result = userService.updateByMail(user);

        assertEquals(retrievedUser.toString(), result.toString());
    }

    @Test
    @DisplayName("When updateByMail With Valid Attributes And Invalid Mail Then Returns NoSuchElementException")
    void updateByMailNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        User retrievedUser = new User();
        retrievedUser.setIdUser(4L);
        retrievedUser.setNickname("Name");

        Throwable exception = Assertions.catchThrowable(() -> {
            User result = userService.updateByMail(retrievedUser);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findUserProfileByUserId With Valid Attributes Then Returns UserProfileResponseDto")
    void findUserProfileByUserId() throws IllegalAccessException {
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        Unlockable avatar = new Unlockable();
        avatar.setIcon("Icon");

        when(userUnlockableRepository.findInUseAvatarByUserId(any())).thenReturn(Optional.of(avatar));

        Rank rank = new Rank();
        rank.setLevel(5);

        UserRank lastActiveRank = new UserRank();
        lastActiveRank.setStatus(1);
        lastActiveRank.setRank(rank);
        lastActiveRank.setCurrentExperience(100);
        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(lastActiveRank));
        
        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto();
        userProfileResponseDto.setAvatar(avatar);
        userProfileResponseDto.setUserRank(lastActiveRank);

        final UserProfileResponseDto result = userService.findUserProfileByUserId(1L);

        assertEquals(result.toString(), userProfileResponseDto.toString());

    }

    @Test
    @DisplayName("When findUserProfileByUserId With Invalid Id Then Returns NoSuchElementException")
    void findUserProfileByUserIdInvalidId() throws IllegalAccessException {
        String expectedMessage = "User does not exist in database";
        User user = new User();

        Throwable exception = Assertions.catchThrowable(() -> {
            UserProfileResponseDto result = userService.findUserProfileByUserId(1L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findUserProfileByUserId With Valid Username And Valid Last Active Rank And Invalid Avatar Then Returns NoSuchElementException")
    void findUserProfileByUserIdInvalidAvatar() throws IllegalAccessException {
        String expectedMessage = "User does not have an active avatar";
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        Throwable exception = Assertions.catchThrowable(() -> {
            UserProfileResponseDto result = userService.findUserProfileByUserId(1L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findUserProfileByUserId With Valid Username And Valid Last Active Rank And Valid Avatar And Invalid UserRank Then Returns NoSuchElementException")
    void findUserProfileByUserIdInvalidUserRank() throws IllegalAccessException {
        String expectedMessage = "User does not hava an active ran";
        User user = new User();
        user.setNickname("Nickname");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        Unlockable avatar = new Unlockable();
        avatar.setIcon("Icon");

        when(userUnlockableRepository.findInUseAvatarByUserId(any())).thenReturn(Optional.of(avatar));

        Throwable exception = Assertions.catchThrowable(() -> {
            UserProfileResponseDto result = userService.findUserProfileByUserId(1L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }
}
