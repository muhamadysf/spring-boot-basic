package com.book_catalog_web.dto.response;

import java.time.LocalDate;

public record AuthorResponseDTO(
        Long id,
        String name,
        String birth_place,
        LocalDate birth_date,
        String description
) {
}
