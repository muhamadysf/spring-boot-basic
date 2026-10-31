package com.book_catalog_web.dto.response;

import com.book_catalog_web.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.Date;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ErrorResponseDTO(
    Date timestamp,
    HttpStatus status,
    String message,
    ErrorCode errorCode,
    List<String> details
) {
}
