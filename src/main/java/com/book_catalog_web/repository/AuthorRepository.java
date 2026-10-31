package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public List<Author> findAuthorsByNameIsLikeIgnoreCase(String name);

    public Set<Author> findAllByIdIn(List<Long> authorId);
}
