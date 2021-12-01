package com.edu.cs.go.bet.auth.service;

import com.edu.cs.go.bet.auth.exception.EmailAlreadyExistsException;
import com.edu.cs.go.bet.auth.exception.UsernameAlreadyExistsException;
import com.edu.cs.go.bet.auth.model.Role;
import com.edu.cs.go.bet.auth.model.User;
import com.edu.cs.go.bet.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProviderImpl tokenProvider;

    public String loginUser(String username, String password) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return tokenProvider.generateToken(authentication);
    }

    public User registerUser(User user, Role role) {
        log.info("registering user {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());

            throw new UsernameAlreadyExistsException(
                    String.format("username %s already exists", user.getUsername()));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());

            throw new EmailAlreadyExistsException(
                    String.format("email %s already exists", user.getEmail()));
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>() {{
            add(role);
        }});

        return userRepository.save(user);
    }

    public List<User> findAll() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        log.info("retrieving user {}", username);
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(String id) {
        log.info("retrieving user {}", id);
        return userRepository.findById(id);
    }
}
