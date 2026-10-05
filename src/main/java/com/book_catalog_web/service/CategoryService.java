package com.book_catalog_web.service;

import com.book_catalog_web.dto.request.CategoryRequestDTO;
import com.book_catalog_web.dto.request.CategoryUpdateRequestDTO;
import com.book_catalog_web.dto.response.CategoryListResponseDTO;
import com.book_catalog_web.dto.response.CategoryResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;

public interface CategoryService {
    public void createCategory(CategoryRequestDTO dto);
    public CategoryResponseDTO findCategoryDetail(String code);
    public ResultPageResponseDTO<CategoryListResponseDTO> findAllCategory(
        Integer pages,
        Integer limit,
        String sortBy,
        String direction,
        String categoryName
    );
    public void updateCategory(CategoryUpdateRequestDTO dto);
    public void deleteCategory(String code);
}
