package com.edu.cs.go.bet.auth.service;

import com.edu.cs.go.bet.auth.model.User;
import com.edu.cs.go.bet.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {

        log.error("Loading user details ...");

        try {
            log.error("Finding user by token " + token.getName());

            var user = repository.findByOpenid(token.getName());

            if (user != null) return user;

            throw new UsernameNotFoundException("User is no found");

        } catch (RuntimeException e) {

            log.error("Exception caught", e);

            var u = new User();
            u.setOpenIdToken(token.getName());

            log.error("Saving user and return ...");
            return repository.save(u);
        }

    }
}
