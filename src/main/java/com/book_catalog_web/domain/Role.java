package com.book_catalog_web.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + this.code;
    }
}
