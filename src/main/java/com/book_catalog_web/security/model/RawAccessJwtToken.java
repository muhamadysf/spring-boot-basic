package com.book_catalog_web.security.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;

@RequiredArgsConstructor
public class RawAccessJwtToken implements Token{

    private final String token;

    public Jws<Claims> parseClaims(SecretKey secretKey){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

    @Override
    public String getToken() {
        return this.token;
    }
}
