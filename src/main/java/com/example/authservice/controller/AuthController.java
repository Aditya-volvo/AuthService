package com.example.authservice.controller;

import com.example.authservice.dto.SignupRequest;
import com.example.authservice.repository.AdminRepository;
import com.example.authservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationManager authenticationManager;
    private  final AdminService adminService;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtUtils jwtUtils;

    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signupRequest){
            adminService.registerAdmin(signupRequest);

    }

}
