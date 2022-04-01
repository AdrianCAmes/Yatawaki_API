package com.orchestrator.orchestrator.configuration;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> retrievedUser = userRepository.findByUniqueIdentifier(username);
        if (retrievedUser.isEmpty()) throw new UsernameNotFoundException("User not found in database");
        User user = retrievedUser.get();
        org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword()).authorities(user.getRole());
        UserDetails u = builder.build();
        return u;
    }
}
