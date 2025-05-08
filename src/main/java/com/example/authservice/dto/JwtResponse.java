package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type="Bearer";
    private Long adminId;
    private String password;

    public JwtResponse(String token,Long adminId,String password){
        this.token=token;
        this.adminId=adminId;
        this.password=password;
    }
}
