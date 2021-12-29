package com.edu.cs.go.bet.auth.controller.impl;


import com.edu.cs.go.bet.auth.controller.AuthController;
import com.edu.cs.go.bet.auth.exception.BadRequestException;
import com.edu.cs.go.bet.auth.exception.EmailAlreadyExistsException;
import com.edu.cs.go.bet.auth.exception.UsernameAlreadyExistsException;
import com.edu.cs.go.bet.auth.model.Profile;
import com.edu.cs.go.bet.auth.model.Role;
import com.edu.cs.go.bet.auth.model.User;
import com.edu.cs.go.bet.auth.payload.*;
import com.edu.cs.go.bet.auth.service.FacebookServiceImpl;
import com.edu.cs.go.bet.auth.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final UserServiceImpl userService;
    private final FacebookServiceImpl facebookServiceImpl;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/facebook/signin")
    public ResponseEntity<?> facebookAuth(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest) {
        log.info("facebook login {}", facebookLoginRequest);
        String token = facebookServiceImpl.loginUser(facebookLoginRequest.getAccessToken());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/steam/signin")
    public ResponseEntity<?> steamAuth() {
        log.info("steam login");
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        User user = User
                .builder()
                .username(payload.getUsername())
                .email(payload.getEmail())
                .password(payload.getPassword())
                .userProfile(Profile
                        .builder()
                        .displayName(payload.getName())
                        .profilePictureUrl(payload.getProfilePicUrl())
                        .build())
                .build();

        try {
            userService.registerUser(user, Role.USER);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true, "User registered successfully"));
    }
}
