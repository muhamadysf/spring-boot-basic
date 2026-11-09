package com.book_catalog_web.security.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String username;
    private RawAccessJwtToken rawAccessJwtToken;


    public JwtAuthenticationToken(RawAccessJwtToken rawAccessJwtToken) {
        super((Collection<? extends GrantedAuthority>) null);
        this.rawAccessJwtToken = rawAccessJwtToken;
        super.setAuthenticated(false);
    }

    public JwtAuthenticationToken(String username, @Nullable Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.username = username;
        this.setAuthenticated(true);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }

    @Override
    public @Nullable Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return this.username;
    }
}
