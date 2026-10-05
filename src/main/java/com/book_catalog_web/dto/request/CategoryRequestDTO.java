package com.book_catalog_web.dto.request;

import jakarta.validation.constraints.NotBlank;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CategoryRequestDTO(
        @NotBlank String code,
        @NotBlank String name,
        String description
) {
}
