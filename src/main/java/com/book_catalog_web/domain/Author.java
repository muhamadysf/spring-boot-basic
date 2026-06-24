package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "author")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity{
    
    @Column(nullable = false)
    private String name;

    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(columnDefinition = "varchar(500)")
    private String description;
}
