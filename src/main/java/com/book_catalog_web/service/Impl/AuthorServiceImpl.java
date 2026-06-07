package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Author;
import com.book_catalog_web.dto.request.AuthorRequestDTO;
import com.book_catalog_web.dto.response.AuthorSearchResponseDTO;
import com.book_catalog_web.dto.response.AuthorResponseDTO;
import com.book_catalog_web.repository.AuthorRepository;
import com.book_catalog_web.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void createNewAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.name());
        author.setBirthPlace(dto.birth_place());
        author.setBirthDate(dto.birth_date());
        author.setDescription(dto.description());

        authorRepository.save(author);
    }

    @Override
    public AuthorResponseDTO findAuthorDetail(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Author tidak ditemukan...!"));

        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getBirthPlace(),
                author.getBirthDate(),
                author.getDescription());
    }

    @Override
    public List<AuthorSearchResponseDTO> searchAuthor(String name) {

        // --- cara 1 --- :
//        List<Author> authors = null;
//        if (name != null) {
//            authors =  authorRepository.findAuthorsByNameIsLikeIgnoreCase("%"+ name +"%");
//        } else {
//           authors = authorRepository.findAll();
//        }

//        return authors.stream().map(author -> new AuthorSearchResponseDTO(
//                author.getId(), author.getName()
//        )).toList();

        // --- cara 2: ---
        name = StringUtils.hasLength(name) ? "%" + name + "%" : "%";
        List<Author> authors =  authorRepository.findAuthorsByNameIsLikeIgnoreCase(name);

        return authors.stream().map(author -> {
            return new AuthorSearchResponseDTO(author.getId(), author.getName());
        }).toList();
    }

    @Override
    public void updateAuthor(Long id, AuthorRequestDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Id Author tidak ditemukan"));

        author.setName(dto.name());
        author.setBirthDate(dto.birth_date());
        author.setBirthPlace(dto.birth_place());
        author.setDescription(dto.description());

        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
/*
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


 */



}
