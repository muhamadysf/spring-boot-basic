package com.book_catalog_web.controller;

import com.book_catalog_web.dto.request.CategoryRequestDTO;
import com.book_catalog_web.dto.request.CategoryUpdateRequestDTO;
import com.book_catalog_web.dto.response.CategoryListResponseDTO;
import com.book_catalog_web.dto.response.CategoryResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryRequestDTO dto){
        categoryService.createCategory(dto);
        return ResponseEntity.created(URI.create("/v1/categories")).build();
    }


    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<List<CategoryListResponseDTO>>> findCategoryList(
            @RequestParam(defaultValue = "0") Integer pages,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name
    ){
        return ResponseEntity.ok(categoryService.findAllCategory(pages, limit, sortBy, direction, name));
    }

    @GetMapping("{code}")
    public ResponseEntity<CategoryResponseDTO> findCategoryDetail(@PathVariable String code){
        return ResponseEntity.ok(categoryService.findCategoryDetail(code));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{code}")
    public ResponseEntity<Void> updateCategory(@PathVariable String code, @RequestBody @Valid CategoryUpdateRequestDTO dto){
        categoryService.updateCategory(code, dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{code}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String code){
        categoryService.deleteCategory(code);
        return ResponseEntity.noContent().build();
    }

}
