package com.book_catalog_web.repository;

import com.book_catalog_web.domain.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.isDeleted = true WHERE a.id = :id")
    public void softDelete(Long id);
}
