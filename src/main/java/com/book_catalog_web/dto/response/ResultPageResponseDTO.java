package com.book_catalog_web.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResultPageResponseDTO<T>(
        List<T> result,
        Integer pages,
        Long elements
) {
}
