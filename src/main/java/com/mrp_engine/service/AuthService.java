package com.mrp_engine.service;

import com.mrp_engine.dto.request.LoginRequest;
import com.mrp_engine.dto.request.RegisterRequest;
import com.mrp_engine.dto.response.AuthResponse;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}