package com.book_catalog_web.dto.query;

public record BookQueryDTO(
        Long bookId,
        String bookTitle,
        String bookDescription,
        Long publisherId,
        String publisherName
) {
}
