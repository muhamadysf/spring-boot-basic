package com.book_catalog_web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookUpdateRequestDTO(
        @NotBlank
        String title,
        String description,
        @NotNull
        Integer pages,
        @NotNull
        Integer publishingYear,
        @NotNull
        Long publisherId,
        @NotEmpty
        List<Long> authorsId,
        @NotEmpty
        List<String> categoriesCode
) {
}
