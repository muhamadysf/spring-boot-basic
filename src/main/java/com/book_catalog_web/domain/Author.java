package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(birthPlace, author.birthPlace) && Objects.equals(birthDate, author.birthDate) && Objects.equals(description, author.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthPlace, birthDate, description);
    }

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
