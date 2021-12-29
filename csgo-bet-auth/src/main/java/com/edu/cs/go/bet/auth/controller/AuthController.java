package com.edu.cs.go.bet.auth.controller;


import com.edu.cs.go.bet.auth.payload.FacebookLoginRequest;
import com.edu.cs.go.bet.auth.payload.LoginRequest;
import com.edu.cs.go.bet.auth.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> facebookAuth(FacebookLoginRequest facebookLoginRequest);

    ResponseEntity<?> createUser(SignUpRequest payload);

    ResponseEntity<?> steamAuth();
}
