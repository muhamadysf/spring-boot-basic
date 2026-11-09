package com.book_catalog_web.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtTokenFactory {

    private final Key key;

    public String createAccessJwtToken(String username, Collection<? extends GrantedAuthority> authorities){

        Claims claims = Jwts.claims().subject(username).add("scope", authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList())).build();

        LocalDateTime currentTime = LocalDateTime.now();
        Date currentTimeDate = Date.from(currentTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());

        LocalDateTime expiredTime = currentTime.plusMinutes(15);
        Date expiredTimeDate = Date.from(expiredTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());

        return Jwts.builder()
                .claims(claims)
                .issuer("https://catalog.com")
                .issuedAt(currentTimeDate)
                .expiration(expiredTimeDate)
                .signWith(key).compact();
    }
}
