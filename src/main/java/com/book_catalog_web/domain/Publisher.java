package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "publisher")
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted", columnDefinition = "boolean default false not null")
    private Boolean isDeleted;
}
