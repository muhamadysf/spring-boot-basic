package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Author;
import com.book_catalog_web.dto.query.AuthorQueryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public List<Author> findAuthorsByNameIsLikeIgnoreCase(String name);

    public Set<Author> findAllByIdIn(List<Long> authorId);

    @Query("""
SELECT new com.book_catalog_web.dto.query.AuthorQueryDTO(b.id, ba.id, ba.name) FROM Book b JOIN b.authors ba WHERE b.id IN :bookIdSet
""")
    public Set<AuthorQueryDTO> findAuthorsByBookId(Set<Long> bookIdSet);
}
