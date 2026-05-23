package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Author;
import com.book_catalog_web.dto.AuthorCreateRequestDTO;
import com.book_catalog_web.dto.AuthorResponseDTO;
import com.book_catalog_web.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private List<Author> authorList;

    public AuthorServiceImpl(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public List<AuthorResponseDTO> findAuthorList() {


        return authorList.stream().map(a -> {
            return new AuthorResponseDTO(a.getName(), a.getDescription());
        }).collect(Collectors.toList());


    }

    @Override
    public void createNewAuthor(AuthorCreateRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.name());
        author.setDescription(dto.description());
        authorList.add(author);
    }
}
