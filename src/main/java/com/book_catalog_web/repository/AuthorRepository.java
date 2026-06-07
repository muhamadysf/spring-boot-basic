package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public List<Author> findAuthorsByNameIsLikeIgnoreCase(String name);
}
