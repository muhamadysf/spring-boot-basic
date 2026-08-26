package com.book_catalog_web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Nama wajib diisi.!")
        String name,
        @NotBlank(message = "email wajib diisi.!")
        String email,
        @Size(max = 20)
        @NotBlank
        String mobileNumber
) {
}
