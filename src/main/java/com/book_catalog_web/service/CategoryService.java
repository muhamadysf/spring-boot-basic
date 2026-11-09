package com.book_catalog_web.service;

import com.book_catalog_web.domain.Category;
import com.book_catalog_web.dto.request.CategoryRequestDTO;
import com.book_catalog_web.dto.request.CategoryUpdateRequestDTO;
import com.book_catalog_web.dto.response.CategoryListResponseDTO;
import com.book_catalog_web.dto.response.CategoryResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CategoryService {
    public void createCategory(CategoryRequestDTO dto);
    public CategoryResponseDTO findCategoryDetail(String code);
    public ResultPageResponseDTO<List<CategoryListResponseDTO>> findAllCategory(
        Integer pages,
        Integer limit,
        String sortBy,
        String direction,
        String categoryName
    );
    public void updateCategory(String code, CategoryUpdateRequestDTO dto);
    public void deleteCategory(String code);
    public Set<Category> findAllCategoies(List<String> code);

    public Map<Long, Set<CategoryListResponseDTO>> findCategoryMap(Set<Long> bookIdSet);
}
