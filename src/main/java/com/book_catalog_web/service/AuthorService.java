package com.book_catalog_web.service;

import com.book_catalog_web.dto.request.AuthorRequestDTO;
import com.book_catalog_web.dto.response.AuthorSearchResponseDTO;
import com.book_catalog_web.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    /*
    public List<AuthorResponseDTO> findAuthorList();

    public void createNewAuthor(AuthorCreateRequestDTO dto);
    */

    public void createNewAuthor(AuthorRequestDTO dto);
    public AuthorResponseDTO findAuthorDetail(Long id);
    public List<AuthorSearchResponseDTO> searchAuthor(String name);
    public void updateAuthor(Long id, AuthorRequestDTO dto);
    public void deleteAuthor(Long id);

}
