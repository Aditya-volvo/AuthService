package com.example.authservice.security;

import com.example.authservice.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl {
    private Long id;
    private String email;
    private String password;

    public static UserDetailsImpl build(Admin admin){
        return new UserDetailsImpl(admin.getAdminId(), admin.getAdminEmail(), admin.getPassword());
    }


}
