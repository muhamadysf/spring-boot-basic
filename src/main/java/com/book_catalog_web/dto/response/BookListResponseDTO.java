package com.book_catalog_web.dto.response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class
)
public record BookListResponseDTO(
        Long id,
        String title,
        PublisherListResponseDTO publisher,
        Set<AuthorListResponseDTO> authors,
        Set<CategoryListResponseDTO> categories
) {
}
