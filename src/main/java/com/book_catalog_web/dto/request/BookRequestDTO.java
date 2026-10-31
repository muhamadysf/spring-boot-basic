package com.book_catalog_web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookRequestDTO(
        @NotBlank
        String title,
        String description,
        Integer pages,
        Integer publishingYear,
        @NotNull
        Long publisherId,
        @NotEmpty
        List<Long> authorsId,
        @NotEmpty
        List<String> categoriesCode
) {
}
