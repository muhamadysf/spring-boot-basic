package com.book_catalog_web.config;

import com.book_catalog_web.security.util.JwtTokenFactory;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import java.security.Key;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Catalog API"))
                .addSecurityItem(new SecurityRequirement().addList("CatalogSecurityScheme"))
                .components(new Components().addSecuritySchemes("CatalogSecurityScheme", new SecurityScheme().name("CatalogSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public SecretKey key() {
        byte[] keyBytes = Decoders.BASE64.decode("kmdksamdksmdskdmkasmdsjdjskfskjfksjdkjdfkasjdk");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public JwtTokenFactory jwtTokenFactory(Key secretKey) {
        return new JwtTokenFactory(secretKey);
    }
}
