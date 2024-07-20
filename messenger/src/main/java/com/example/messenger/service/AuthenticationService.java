package com.example.messenger.service;

import com.example.messenger.dto.Jwt.JwtAuthenticationResponse;
import com.example.messenger.dto.SignInRequest;
import com.example.messenger.dto.SignUpRequest;
import jakarta.validation.Valid;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(@Valid SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
