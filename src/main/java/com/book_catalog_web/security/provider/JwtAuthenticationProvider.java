package com.book_catalog_web.security.provider;

import com.book_catalog_web.security.model.JwtAuthenticationToken;
import com.book_catalog_web.security.model.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final SecretKey key;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RawAccessJwtToken rawToken = (RawAccessJwtToken) authentication.getCredentials();

        if (StringUtils.isBlank(rawToken.getToken())) {
            return new JwtAuthenticationToken(null, List.of(new SimpleGrantedAuthority("ROLE_GUEST")));
        }

        Jws<Claims> jwsClaims = rawToken.parseClaims(key);
        String subject = jwsClaims.getPayload().getSubject();
        List<String> scopes = jwsClaims.getPayload().get("scope", List.class);

        return new JwtAuthenticationToken(subject, scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
