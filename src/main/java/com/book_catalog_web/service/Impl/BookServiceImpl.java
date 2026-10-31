package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Author;
import com.book_catalog_web.domain.Book;
import com.book_catalog_web.domain.Category;
import com.book_catalog_web.domain.Publisher;
import com.book_catalog_web.dto.request.BookRequestDTO;
import com.book_catalog_web.dto.request.BookUpdateRequestDTO;
import com.book_catalog_web.dto.response.*;
import com.book_catalog_web.exception.ResourceNotFoundException;
import com.book_catalog_web.repository.BookRepository;
import com.book_catalog_web.service.AuthorService;
import com.book_catalog_web.service.BookService;
import com.book_catalog_web.service.CategoryService;
import com.book_catalog_web.service.PublisherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public IdResponseDTO createBook(BookRequestDTO dto) {

        // 1. find author list by authorId
        Set<Author> authors = authorService.findAllAuthors(dto.authorsId());
        // 2. find category list by categoryCode
        Set<Category> categories = categoryService.findAllCategoies(dto.categoriesCode());
        // 3. find publisher by publisherId
        Publisher publisher = publisherService.findPublisher(dto.publisherId());

        // 4. dto -> entity
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthors(authors);
        book.setDescription(dto.description());
        book.setCategories(categories);
        book.setPages(dto.pages());
        book.setPublisher(publisher);
        book.setPublisherYear(dto.publishingYear());

        // 5. save
        book = bookRepository.save(book);

        // 6. Kembalikan ke API/Controller
        return new IdResponseDTO(book.getId());
    }

    @Override
    public BookResponseDTO findBookDetail(Long id) {

        // get book by id
         Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found..!"));

        // get publisher
        Publisher publisher = book.getPublisher();

        // get authors
        Set<Author> authors = book.getAuthors();

        // get categories
        Set<Category> categories = book.getCategories();

        // ----[tahapan mapping] --------

        // author --> dto
        Set<AuthorListResponseDTO> authorDTO = authors.stream().map(a -> {
            return new AuthorListResponseDTO(a.getId(), a.getName());
        }).collect(Collectors.toSet());

        // category --> dto
        Set<CategoryListResponseDTO> categoriesDTO = categories.stream().map(c -> {
            return new CategoryListResponseDTO( c.getCode(), c.getName());
        }).collect(Collectors.toSet());

        // publisher ---> dto
        PublisherListResponseDTO publisherDTO = new PublisherListResponseDTO(publisher.getId(), publisher.getName());

        // book ---> dto
        return new BookResponseDTO(book.getId(), book.getTitle(), book.getDescription(), book.getPages(), book.getPublisherYear(), publisherDTO, authorDTO, categoriesDTO);
    }

    @Transactional
    @Override
    public void updateBook(Long id, BookUpdateRequestDTO dto) {

        Set<Author> authors = authorService.findAllAuthors(dto.authorsId());
        Set<Category> categories = categoryService.findAllCategoies(dto.categoriesCode());
        Publisher publisher = publisherService.findPublisher(dto.publisherId());

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found.!"));

        book.setTitle(dto.title());
        book.setAuthors(authors);
        book.setDescription(dto.description());
        book.setCategories(categories);
        book.setPages(dto.pages());
        book.setPublisher(publisher);
        book.setPublisherYear(dto.publishingYear());

        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found.!"));

        bookRepository.delete(book);
    }

}
