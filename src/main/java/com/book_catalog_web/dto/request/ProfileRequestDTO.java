package com.book_catalog_web.dto.request;

import jakarta.validation.constraints.Past;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProfileRequestDTO(
        @Past
        LocalDate birthDate,
        String address,
        String zipCode
) {
}
