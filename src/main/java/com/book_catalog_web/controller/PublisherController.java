package com.book_catalog_web.controller;

import com.book_catalog_web.dto.*;
import com.book_catalog_web.dto.response.PublisherListResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
// @Controller --> jika digunakan maka pada setiap method di dalamnya harus ditambahkan annotasi @ResponseBody
@RestController // ---> pengganti untuk @Controller. Sehingga pada method yang ada didalamnya tidak perlu using @ResponseBody
@RequestMapping("/v1/publishers") // ---> nama endpoint API harus kata benda, jamak, dan match dengan nama class.
public class PublisherController {

    private final PublisherService publisherService;

    //@GetMapping
    //@ResponseBody  --> annotasi yang memberitahu servelt kalau return-nya bukan nama view, malainkan body response JSON/XML
    public ResponseEntity<PublisherListResponseDTO> hello(){
        // cara 1. menggunakan DTO dengan class (traditional/POJO)
        // PublisherListResponseDTO dto = new PublisherListResponseDTO();
        // dto.setId(1L);
        // dto.setName("Zervash");
        // return dto;
    // ------------------------------------------------------------------
        // cara 2. menggunakan DTO dengan record (java 14)
        return ResponseEntity.ok(new PublisherListResponseDTO(1L, "Zervash"));
    }
// ====================================================================================================================

    @GetMapping("{id}")
    public ResponseEntity<PublisherDetailResponseDTO> findPublisherDetail(@PathVariable Long id){
        PublisherDetailResponseDTO dto = publisherService.findPublisherDetail(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> createNewPublisher(@RequestBody PublisherCreateRequestDTO dto){
        publisherService.createPublisher(dto);
        return ResponseEntity.created(URI.create("/v1/publishers")).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePublisher(@PathVariable Long id, @RequestBody PublisherUpdateRequestDTO dto){
        publisherService.updatePublisher(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
            @RequestParam(defaultValue = "0") Integer pages,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String publisherName
    ){
        return ResponseEntity.ok(publisherService.findAllPublisher(pages, limit, sortBy, direction, publisherName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }



}
