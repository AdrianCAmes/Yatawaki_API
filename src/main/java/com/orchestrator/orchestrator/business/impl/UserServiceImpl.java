package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserService;
import com.orchestrator.orchestrator.configuration.JwtUtil;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.user.request.UserAuthenticateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserAuthenticateResponseDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.UserStatisticsUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    // Self repository
    private final UserRepository userRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserStatisticsUtils userStatisticsUtils;
    private final UserRankUtils userRankUtils;
    private final UserUnlockableUtils userUnlockableUtils;
    // Other Repositories
    private final UserStatisticsRepository userStatisticsRepository;
    private final RankRepository rankRepository;
    private final UserRankRepository userRankRepository;
    private final UnlockableRepository unlockableRepository;
    private final UserUnlockableRepository userUnlockableRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtilService;

    // region CRUD Operations
    @Override
    public User create(User user) {
        if (user.getIdUser() != null) throw new IllegalArgumentException("Body should not contain id");
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User change(User user) {
        User userToChange = findById(user.getIdUser());
        if (userToChange != null) {
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public User update(User user) throws IllegalAccessException {
        User userToUpdate = findById(user.getIdUser());
        if (userToUpdate != null) {
            generalUtils.mapFields(user, userToUpdate);
            return userRepository.save(userToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public User removeById(Long id) {
        User userToDelete = findById(id);
        if (userToDelete != null) {
            userRepository.deleteById(id);
            return userToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    public User register(User user) throws IllegalAccessException {
        // Create new user statistics for user
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        UserStatistics userStatisticsToSave = userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto);
        UserStatistics createdUserStatistics = userStatisticsRepository.save(userStatisticsToSave);

        // Create new user
        user.setUserStatistics(createdUserStatistics);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createdUser = create(user);

        // Set user rank to one
        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(createdUser.getIdUser());
        userRankCreateRequestDto.setIdRank(rankRepository.findByLevel(NumericConstants.ONE.getValue()).getIdRank());
        UserRank userRankToSave = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
        userRankRepository.save(userRankToSave);

        // Grant basic unlockable package
        List<Unlockable> baseUnlockables = unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getName(), 1);
        for(Unlockable unlockable : baseUnlockables) {
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUser(createdUser.getIdUser());
            userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
            UserUnlockable userUnlockableToSave = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
            userUnlockableRepository.save(userUnlockableToSave);
        }
        return createdUser;
    }

    @Override
    public UserAuthenticateResponseDto authenticate(UserAuthenticateRequestDto userAuthenticateRequestDto) {
        UserAuthenticateResponseDto userAuthenticateResponseDto = new UserAuthenticateResponseDto();
        List<GrantedAuthority> grantedAuths = AuthorityUtils.commaSeparatedStringToAuthorityList("user");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticateRequestDto.getUniqueIdentifier(),
                userAuthenticateRequestDto.getPassword(), grantedAuths));

        final UserDetails userDetails = loadUserByUsername(userAuthenticateRequestDto.getUniqueIdentifier());

        final String jwt = jwtUtilService.generateToken(userDetails);
        Optional<User> retrievedUser = userRepository.findByUniqueIdentifier(userAuthenticateRequestDto.getUniqueIdentifier());

        if (retrievedUser.isPresent()) {
            userAuthenticateResponseDto.setIsAuthenticated(Boolean.TRUE);
            userAuthenticateResponseDto.setJwt(jwt);
            userAuthenticateResponseDto.setRole(retrievedUser.get().getRole());
        } else {
            userAuthenticateResponseDto.setIsAuthenticated(Boolean.FALSE);
            userAuthenticateResponseDto.setJwt(null);
        }
        return userAuthenticateResponseDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> retrievedUserByEmail = userRepository.findByEmail(username);
        Optional<User> retrievedUserByNick = userRepository.findByNickname(username);
        if (retrievedUserByEmail.isPresent()) {
            User user = retrievedUserByEmail.get();
            org.springframework.security.core.userdetails.User.UserBuilder builder = null;
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword()).roles("USUARIO");
            return builder.build();

        } else if (retrievedUserByNick.isPresent()) {
            User user = retrievedUserByNick.get();
            org.springframework.security.core.userdetails.User.UserBuilder builder = null;
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword()).roles("USUARIO");
            return builder.build();
        }

        throw new NoSuchElementException("Element does not exist in database");
    }
    // endregion Use Cases External

    // region Use Cases Internal

    // endregion Use Cases Internal
    // endregion Use Cases
}
