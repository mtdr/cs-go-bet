package com.edu.cs.go.bet.auth.service;

import com.edu.cs.go.bet.auth.model.Role;
import com.edu.cs.go.bet.auth.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String loginUser(String username, String password);

    User registerUser(User user, Role role);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(String id);
}