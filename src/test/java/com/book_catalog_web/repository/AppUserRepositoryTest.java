package com.book_catalog_web.repository;

import com.book_catalog_web.domain.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    public void testSave(){
        AppUser appUser = new AppUser();

        appUser.setName("Shiro");
        appUser.setMobileNumber("081317227");
        appUser.setEmail("mail@y.com");

        appUserRepository.save(appUser);
    }
}
