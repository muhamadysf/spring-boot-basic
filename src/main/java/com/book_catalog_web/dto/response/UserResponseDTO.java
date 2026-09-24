package com.book_catalog_web.dto.response;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserResponseDTO(
        Long id,
        UUID secureId,
        String name,
        String email,
        String mobileNumber
) {
}
