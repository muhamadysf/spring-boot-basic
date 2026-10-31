package com.book_catalog_web.controller;

import com.book_catalog_web.dto.request.BookRequestDTO;
import com.book_catalog_web.dto.request.BookUpdateRequestDTO;
import com.book_catalog_web.dto.response.BookResponseDTO;
import com.book_catalog_web.dto.response.IdResponseDTO;
import com.book_catalog_web.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<IdResponseDTO> createBook(@RequestBody @Valid BookRequestDTO dto){
        //return ResponseEntity.ok(bookService.createBook(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDTO> findBookDetail(@PathVariable Long id){
        return ResponseEntity.ok(bookService.findBookDetail(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequestDTO dto){
        bookService.updateBook(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
