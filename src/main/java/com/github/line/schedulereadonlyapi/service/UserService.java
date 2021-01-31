package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.domain.User;
import com.github.line.schedulereadonlyapi.exception.UsernameTakenException;
import com.github.line.schedulereadonlyapi.repository.UserRepository;
import com.github.line.schedulereadonlyapi.security.SecurityContextExtractor;
import com.github.line.schedulereadonlyapi.security.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final String USER_ROLE = "USER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with given username"));
    }

    public Optional<User> getUser() {
        Optional<String> currentUser = SecurityContextExtractor.getCurrentUser();
        if (currentUser.isPresent()) {
            User user = (User) loadUserByUsername(currentUser.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public User saveUser(SignUpRequest signUpRequest) {
        if (isUsernameTaken(signUpRequest.getUsername())) {
            throw new UsernameTakenException("User with this username already exists!");
        }

        User user = new User(
                null,
                signUpRequest.getUsername(),
                signUpRequest.getPassword(),
                USER_ROLE,
                signUpRequest.getGroupId(),
                true);
        return repository.save(user);
    }

    private boolean isUsernameTaken(String username) {
        Optional<User> byUsername = repository.findByUsername(username);
        return byUsername.isPresent();
    }
}
