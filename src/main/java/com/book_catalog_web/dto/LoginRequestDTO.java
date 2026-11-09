package com.book_catalog_web.dto;

public record LoginRequestDTO(
        String username,
        String password
) {
}
