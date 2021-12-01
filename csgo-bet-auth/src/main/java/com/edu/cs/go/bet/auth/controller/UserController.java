package com.edu.cs.go.bet.auth.controller;

import com.edu.cs.go.bet.auth.model.InstaUserDetails;
import com.edu.cs.go.bet.auth.payload.UserSummary;
import org.springframework.http.ResponseEntity;


public interface UserController {

    ResponseEntity<?> findUser(String username);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findAllUserSummaries(InstaUserDetails userDetails);

    UserSummary getCurrentUser(InstaUserDetails userDetails);

    ResponseEntity<?> getUserSummary(String username);

}