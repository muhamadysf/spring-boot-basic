package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@SQLRestriction("is_deleted=false")
@Data
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "pages")
    private String pages;

    @Column(name = "publisher_year")
    private Integer publisherYear;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "book_category",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "category_code"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getBooks().remove(this);
    }


    @ManyToMany
    @JoinTable(name = "book_author",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

}
