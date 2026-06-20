package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Data
public class AppUser {
    /** data
     * long id
     * UUID secureId
     * String name
     * String email
     * String mobileNumber
     * Boolean isDeleted
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "secure_id", nullable = false, unique = true)
    private UUID secureId;


    private String name;

    private String email;

    private String mobileNumber;

    private Boolean isDeleted;

}