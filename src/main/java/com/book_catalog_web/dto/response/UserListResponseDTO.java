package com.book_catalog_web.dto.response;

import java.util.UUID;

public record UserListResponseDTO(
        Long id,
        UUID secureId,
        String name
) {
}
