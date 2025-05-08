package com.example.authservice.service.serviceImpl;

import com.example.authservice.dto.JwtResponse;
import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.SignupRequest;
import com.example.authservice.model.Admin;
import com.example.authservice.repository.AdminRepository;
import com.example.authservice.security.JwtUtils;
import com.example.authservice.security.UserDetailsImpl;
import com.example.authservice.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private  final AdminRepository adminRepository;
    private  final AuthenticationManager authenticationManager;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtUtils jwtUtils;
    @Override
    public ResponseEntity<?> registerAdmin(SignupRequest signUpRequest) {
        if(adminRepository.existsByEmail()){
           return ResponseEntity.badRequest()
                    .body("Error: Email Id already exists !");
        }

        Admin admin = new Admin();
        admin.setAdminEmail(signUpRequest.getAdminEmail());
        admin.setPhoneNumber(signUpRequest.getPhoneNumber());
        admin.setPassword(signUpRequest.getPassword());

        adminRepository.save(admin);
        return ResponseEntity.ok("Admin with id has been registered");
    }

    @Override
    public ResponseEntity<?> adminAuthetication(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getAdminEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= jwtUtils.generateJwtTokens(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return  ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),userDetails.getEmail()));
    }
}
