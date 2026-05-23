package com.book_catalog_web.service;

import com.book_catalog_web.dto.AuthorCreateRequestDTO;
import com.book_catalog_web.dto.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    public List<AuthorResponseDTO> findAuthorList();

    public void createNewAuthor(AuthorCreateRequestDTO dto);
}
