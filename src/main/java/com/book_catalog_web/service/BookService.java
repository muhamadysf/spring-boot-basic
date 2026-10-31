package com.book_catalog_web.service;

import com.book_catalog_web.dto.request.BookRequestDTO;
import com.book_catalog_web.dto.request.BookUpdateRequestDTO;
import com.book_catalog_web.dto.response.BookResponseDTO;
import com.book_catalog_web.dto.response.IdResponseDTO;

public interface BookService {

    public IdResponseDTO createBook(BookRequestDTO dto);

    public BookResponseDTO findBookDetail(Long id);

    public void updateBook(Long id, BookUpdateRequestDTO dto);

    public void deleteBook(Long id);
}
