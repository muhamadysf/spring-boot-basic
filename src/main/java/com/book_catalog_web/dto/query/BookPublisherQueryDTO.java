package com.book_catalog_web.dto.query;

public record BookPublisherQueryDTO(
        Long bookId,
        String bookTitle,
        String bookDescription,
        Long publisherId,
        String publisherName
) {
}
