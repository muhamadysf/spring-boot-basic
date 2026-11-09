package com.book_catalog_web.dto.query;

public record CategoryQueryDTO(
        Long bookId,
        String categoryCode,
        String categoryName
) {
}
