package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Book;
import com.book_catalog_web.dto.query.BookQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query("""
//    SELECT new com.book_catalog_web.dto.query.BookQueryDTO(b.id, b.title, b.description, p.id, p.name) FROM Book b JOIN Publisher p ON b.publisher.id = p.id WHERE b.id=:id
//""")
//    public Optional<BookQueryDTO> findBookByIdUsingJpaProjection(Long id);

    @Query("""
    SELECT new com.book_catalog_web.dto.query.BookQueryDTO(b.id, b.title, b.description, p.id, p.name) FROM Book b JOIN Publisher p ON b.publisher.id = p.id WHERE UPPER(b.title) LIKE UPPER(:title)
""")
    public Page<BookQueryDTO> findBookList(String title, Pageable pageable);
}
