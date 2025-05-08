package com.example.authservice.service;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminService {
    ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signUpRequest);

    ResponseEntity<?> adminAuthetication(@Valid LoginRequest loginRequest);
}
