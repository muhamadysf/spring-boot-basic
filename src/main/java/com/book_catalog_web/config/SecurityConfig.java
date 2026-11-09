package com.book_catalog_web.config;

import com.book_catalog_web.security.filter.JwtAuthProcessingFilter;
import com.book_catalog_web.security.filter.UsernamePasswordAuthProcessFilter;
import com.book_catalog_web.security.handler.UsernamePasswordAuthFailureHandler;
import com.book_catalog_web.security.handler.UsernamePasswordAuthSuccessHandler;
import com.book_catalog_web.security.provider.JwtAuthenticationProvider;
import com.book_catalog_web.security.provider.UsernamePasswordAuthProvider;
import com.book_catalog_web.security.util.JwtTokenFactory;
import com.book_catalog_web.security.util.SkipPathRequestMatcher;
import com.book_catalog_web.security.util.TokenExtractor;
import com.book_catalog_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final static String AUTH_URL = "/v1/login";
    private final static String V1_URL = "/v1/**";

    private final static List<String> PERMIT_ENDPOINT_LIST = Arrays.asList(AUTH_URL,
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui/index.css",
            "/favicon.ico",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui.css.map",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui-standalone-preset.js.map",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-bundle.js.map",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/swagger-ui/swagger-initializer.js",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs");

    private final static List<String> AUTHENTICATED_ENDPOINT_LIST = Arrays.asList(V1_URL);

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public AuthenticationSuccessHandler usernamePasswordSuccessHandler(ObjectMapper objectMapper, JwtTokenFactory jwtTokenFactory){
        return new UsernamePasswordAuthSuccessHandler(objectMapper, jwtTokenFactory);
    }

    @Bean
    public AuthenticationFailureHandler usernamePasswordFailesHandler(ObjectMapper objectMapper){
        return new UsernamePasswordAuthFailureHandler(objectMapper);
    }



    @Autowired
    void resgisterProvider(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthProvider).authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UsernamePasswordAuthProcessFilter usernamePasswordAuthProcessFilter(ObjectMapper objectMapper, AuthenticationFailureHandler failureHandler, AuthenticationSuccessHandler successHandler, AuthenticationManager authenticationManager){
        UsernamePasswordAuthProcessFilter filter = new UsernamePasswordAuthProcessFilter(
                AUTH_URL, objectMapper, successHandler, failureHandler
        );

        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


    @Bean
    public JwtAuthProcessingFilter jwtAuthProcessingFilter(TokenExtractor tokenExtractor, AuthenticationFailureHandler failureHandler, AuthenticationManager manager){
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(PERMIT_ENDPOINT_LIST, AUTHENTICATED_ENDPOINT_LIST);
        JwtAuthProcessingFilter filter = new JwtAuthProcessingFilter(matcher, tokenExtractor, failureHandler);
        filter.setAuthenticationManager(manager);
        return filter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsernamePasswordAuthProcessFilter usernamePasswordAuthProcessFilter, JwtAuthProcessingFilter jwtAuthProcessingFilter){
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(PERMIT_ENDPOINT_LIST.toArray(new String[0])).permitAll()
                        .requestMatchers(V1_URL).authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(usernamePasswordAuthProcessFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(jwtAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
