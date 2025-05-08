package com.example.authservice.service.serviceImpl;

import com.example.authservice.dto.SignupRequest;
import com.example.authservice.model.Admin;
import com.example.authservice.repository.AdminRepository;
import com.example.authservice.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private  final AdminRepository adminRepository;
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
}
