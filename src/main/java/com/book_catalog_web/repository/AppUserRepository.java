package com.book_catalog_web.repository;

import com.book_catalog_web.domain.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.isDeleted = true WHERE a.id = :id")
    public void softDelete(Long id);

    public Page<AppUser> findAppUsersByNameLikeIgnoreCase(String name, Pageable pageable);

    public Optional<AppUser> findAppUserBySecureId(UUID secureId);
}
