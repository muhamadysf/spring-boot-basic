package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.processing.SQL;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@SQLRestriction("is_deleted = false")
@Data
public class AppUser extends BaseUUIDEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

}