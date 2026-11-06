package com.darktolight.api.services;

import com.darktolight.api.config.JwtService;
import com.darktolight.api.models.User;
import com.darktolight.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);

        // Bima anana derna mapping mn User dialna l-User dial Spring Security
        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), java.util.Collections.emptyList());

        return jwtService.generateToken(securityUser);
    }

    public String login(String email, String password) {
        // Hada kay-t-esti wach mot de passe s7i7 (ila ghalet kay-throwi exception)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        User user = userRepository.findByEmail(email).orElseThrow();

        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), java.util.Collections.emptyList());

        return jwtService.generateToken(securityUser);
    }
}