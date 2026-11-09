package com.book_catalog_web.dto.query;

public record AuthorQueryDTO(
        Long bookId,
        Long authorId,
        String authorName
) {
}
