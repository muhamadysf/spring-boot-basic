package com.book_catalog_web.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class PasswordEncryptedTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encryptedPassword() {
        log.info("password yang di hash: {}", passwordEncoder.encode("test123"));
    }
}
