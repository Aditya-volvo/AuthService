package com.example.authservice.controller;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.SignupRequest;
import com.example.authservice.repository.AdminRepository;
import com.example.authservice.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AdminService adminService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignupRequest signupRequest){
       return  adminService.registerAdmin(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminAuthentication(@Valid @RequestBody LoginRequest loginRequest){
        return adminService.adminAuthetication(loginRequest);
    }

}
