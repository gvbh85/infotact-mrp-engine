package com.mrp_engine.service.impl;

import com.mrp_engine.dto.request.LoginRequest;
import com.mrp_engine.dto.request.RegisterRequest;
import com.mrp_engine.dto.response.AuthResponse;
import com.mrp_engine.entity.User;
import com.mrp_engine.repository.UserRepository;
import com.mrp_engine.security.CustomUserDetails;
import com.mrp_engine.security.JwtService;
import com.mrp_engine.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";

    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getUsername(),

                        request.getPassword()

                )

        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponse(

                token,

                user.getUsername(),

                user.getRole().name()

        );

    }

}