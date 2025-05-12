package com.example.authservice.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${authservice.jwtSecret}")
    private String jwtSecret;
    @Value("${authservice.jwtExpirationMs}")
    private String jwtExpirationMs;


    public String generateJwtTokens(Authentication authentication) {
        UserDetailsImpl userPrinciples = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrinciples.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+ jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getEmailFromJwtTokens(String tokens){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(tokens).getBody().getSubject();
    }

    public boolean valideJwtTokens(String authTokens){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authTokens);
            return true;
        }
        catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String parseJwt(HttpServletRequest request) {
        String headAuth = request.getHeader("Authorization");
        if(headAuth != null && headAuth.startsWith("Bearer")){
            return  headAuth.substring(7);
        }
        return null;
    }
}
