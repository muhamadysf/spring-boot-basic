package com.book_catalog_web.dto.response;

import com.book_catalog_web.domain.Author;
import com.book_catalog_web.domain.Publisher;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookResponseDTO(
    Long id,
    String title,
    String description,
    Integer pages,
    Integer publishingYear,
    PublisherListResponseDTO publisher,
    Set<AuthorListResponseDTO> authors,
    Set<CategoryListResponseDTO> categories
) {
}
