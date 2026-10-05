package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Data
public class Profile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
}
