package com.mrp_engine.controller;

import com.mrp_engine.dto.request.LoginRequest;
import com.mrp_engine.dto.request.RegisterRequest;
import com.mrp_engine.dto.response.AuthResponse;
import com.mrp_engine.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(

            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(

                authService.register(request)

        );

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(

            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(

                authService.login(request)

        );

    }

}