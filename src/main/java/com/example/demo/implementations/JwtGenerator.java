package com.example.demo.implementations;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.example.demo.dto.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtGenerator
{
    private final String KEY = ".@:$..x2jfB.!@.\t...\\...!3nQ:.....9cP..%^..n_J";
    private final long EXPIRATION = 360000000;

    public String get(Token Token)
    {
        HashMap<String, Object> Claims = new HashMap<>();
        Claims.put("userId", Token.userId());

        return Get(Claims);
    }

    public Token validate(String JWT)
    {
        try
        {
            Map<String, Object> Map = ValidateJwt(JWT);
            return new Token
            (
                Long.parseLong(Map.get("userId").toString())
            );
        }catch(Exception Ex)
        {
            Ex.printStackTrace();
            return null;
        }
    }

    private String Get(Map<String, Object> customClaims) {
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
        .claims()
        .add(customClaims)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .and()
        .signWith(key)
        .compact();
    }

    private Map<String, Object> ValidateJwt(String jwt)
    {
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.US_ASCII));
        Claims claims = Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(jwt)
        .getPayload();
        
        return new HashMap<>(claims);
    }
}
