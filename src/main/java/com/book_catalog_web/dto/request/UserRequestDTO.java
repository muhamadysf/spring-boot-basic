package com.book_catalog_web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRequestDTO(
        @NotBlank(message = "Nama wajib diisi.!")
        String name,
        @NotBlank(message = "email wajib diisi.!")
        @Email
        String email,

        //@Size(max = 20)
        @NotBlank @Pattern(regexp = "^\\+\\d{1,3}-\\d{10,13}$")
        String mobileNumber
) {
}
