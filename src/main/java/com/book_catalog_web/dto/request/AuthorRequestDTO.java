package com.book_catalog_web.dto.request;


import java.time.LocalDate;

public record AuthorRequestDTO(
    String name,
    String birth_place,
    LocalDate birth_date,
    String description
) {
}








