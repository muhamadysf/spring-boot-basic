package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Category;
import com.book_catalog_web.dto.request.CategoryRequestDTO;
import com.book_catalog_web.dto.request.CategoryUpdateRequestDTO;
import com.book_catalog_web.dto.response.CategoryListResponseDTO;
import com.book_catalog_web.dto.response.CategoryResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.repository.CategoryRepository;
import com.book_catalog_web.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryRequestDTO dto) {
        Category category = new Category();

        category.setCode(dto.code());
        category.setName(dto.name());
        category.setDescription(dto.description());

        categoryRepository.save(category);
    }

    @Override
    public CategoryResponseDTO findCategoryDetail(String code) {
        Category category = categoryRepository.findCategoryByCodeIgnoreCase(code).orElseThrow(() -> new RuntimeException("Category not found"));

        return new CategoryResponseDTO(category.getCode(), category.getName(), category.getDescription());
    }

    @Override
    public ResultPageResponseDTO<CategoryListResponseDTO> findAllCategory(Integer pages, Integer limit, String sortBy, String direction, String categoryName) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        categoryName = StringUtils.isBlank(categoryName) ? "%" : "%" + categoryName + "%";

        Page<Category> resultPage = categoryRepository.

        return null;
    }

    @Override
    public void updateCategory(CategoryUpdateRequestDTO dto) {

    }

    @Override
    public void deleteCategory(String code) {

    }
}
