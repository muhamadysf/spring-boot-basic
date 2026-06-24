package com.book_catalog_web.dto.request;


import com.book_catalog_web.validator.annotation.PastDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequestDTO(
    @NotBlank(message = "Nama wajib diisi.!")
    String name,
    @NotBlank(message = "Tempat lahir wajib diisi.!")
    String birth_place,
    @NotNull(message = "Tanggal lahir wajib diisi.!")
    @Past
//    @PastDate
    LocalDate birth_date,
    @Size(max = 500)
    String description
) {
}








