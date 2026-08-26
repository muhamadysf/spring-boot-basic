package com.book_catalog_web.dto.response;

import java.util.UUID;

public record UserResponseDTO(
        Long id,
        UUID secureId,
        String name,
        String email,
        String mobileNumber
) {
}
