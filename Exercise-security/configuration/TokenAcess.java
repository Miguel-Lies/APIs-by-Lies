package com.study.of.security.lies.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenAcess {

    @Value("${jwt.expiration}")
    private Long timeExpiration;

    @Value("${jwt." +
            "key}")
    private String key;

    public String generateToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return buildCode(userDetails.getUsername());
    }
    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String buildCode(String email){
        Date nowDate=new Date();
        Date expirationDate=new Date(nowDate.getTime()+timeExpiration);

        return Jwts.builder()
                .subject(email)
                .issuedAt(nowDate)
                .expiration(expirationDate)
                .signWith(getSigninKey())
                .compact();
    }
    public String getUsername(String token){
        return getClaims(token).getSubject();
    }
}