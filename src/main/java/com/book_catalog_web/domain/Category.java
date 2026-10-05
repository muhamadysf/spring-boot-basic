package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;

@Entity
@Table(name = "category")
@SQLRestriction("is_deleted=false")
@Data
public class Category extends BaseEntity{

    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;
}
