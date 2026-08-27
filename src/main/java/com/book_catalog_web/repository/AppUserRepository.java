package com.book_catalog_web.repository;

import com.book_catalog_web.domain.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.isDeleted = true WHERE a.id = :id")
    public void softDelete(Long id);

    @Query("""
        SELECT u FROM AppUser u WHERE u.name LIKE LOWER(:userName)
                OR u.email LIKE LOWER(:email)
                OR u.mobileNumber LIKE LOWER(:mobileNumber)
        """)
    public Page<AppUser> findAllUser(String userName, String email, String mobileNumber, Pageable pageable);
}
