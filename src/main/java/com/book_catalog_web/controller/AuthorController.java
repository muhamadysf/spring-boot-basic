package com.book_catalog_web.controller;

import com.book_catalog_web.dto.request.AuthorRequestDTO;
import com.book_catalog_web.dto.response.AuthorSearchResponseDTO;
import com.book_catalog_web.dto.response.AuthorResponseDTO;
import com.book_catalog_web.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/authors")
public class AuthorController {

    /*
    private final AuthorService authorService;

    @GetMapping("new")
    public String displayCreateAuthorForm(Model model){
        model.addAttribute("authorDTO", new AuthorCreateRequestDTO(null, null));
        return "author/author-new";
    }

    @PostMapping("new")
    public String createNewAuthor(@ModelAttribute("authorDTO") AuthorCreateRequestDTO dto, RedirectAttributes redirectAttr){
        log.info("author name : " + dto.name());
        log.info("description : " + dto.description());
        authorService.createNewAuthor(dto);
        redirectAttr.addFlashAttribute("authorDTO", dto);
        return "redirect:/author/create-result";
    }

    @GetMapping("list")
    public String displayAuthorList(Model model){
        List<AuthorResponseDTO> authorList = authorService.findAuthorList();
        model.addAttribute("authors", authorList);
        return "author/author-list";
    }

    @GetMapping("create-result")
    public String displayCreateResult(Model model){
        return "author/author-create-result";
    }
    */

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Void> createNewAuthor(@RequestBody AuthorRequestDTO dto){
        authorService.createNewAuthor(dto);
        return ResponseEntity.created(URI.create("/v1/authors")).build();
    }

    @GetMapping("{authorId}")
    public ResponseEntity<AuthorResponseDTO> findAuthorDetail(@PathVariable Long authorId){
        AuthorResponseDTO author = authorService.findAuthorDetail(authorId);
        return ResponseEntity.ok(author);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorSearchResponseDTO>> searchAuthor(@RequestParam(required = false) String name){
        List<AuthorSearchResponseDTO> dto = authorService.searchAuthor(name);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{authorId}")
    public ResponseEntity<Void> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorRequestDTO dto){
        authorService.updateAuthor(authorId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId){
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }

}
