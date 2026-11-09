package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Category;
import com.book_catalog_web.dto.query.CategoryQueryDTO;
import com.book_catalog_web.dto.request.CategoryRequestDTO;
import com.book_catalog_web.dto.request.CategoryUpdateRequestDTO;
import com.book_catalog_web.dto.response.CategoryListResponseDTO;
import com.book_catalog_web.dto.response.CategoryResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.exception.ResourceNotFoundException;
import com.book_catalog_web.repository.CategoryRepository;
import com.book_catalog_web.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Category category = categoryRepository.findCategoryByCodeIgnoreCase(code).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return new CategoryResponseDTO(category.getCode(), category.getName(), category.getDescription());
    }

    @Override
    public ResultPageResponseDTO<List<CategoryListResponseDTO>> findAllCategory(Integer pages, Integer limit, String sortBy, String direction, String categoryName) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        categoryName = StringUtils.isBlank(categoryName) ? "%" : "%" + categoryName + "%";

        Page<Category> resultPage = categoryRepository.findAllByNameLikeIgnoreCase(categoryName, pageable);

        List<CategoryListResponseDTO> resultDTO = resultPage.getContent().stream().map((c) -> new CategoryListResponseDTO(c.getCode(), c.getName())).toList();

        return new ResultPageResponseDTO(resultDTO, resultPage.getTotalPages(), resultPage.getTotalElements());
    }

    @Transactional
    @Override
    public void updateCategory(String code, CategoryUpdateRequestDTO dto) {
        Category category = categoryRepository.findCategoryByCodeIgnoreCase(code).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(dto.name());
        category.setDescription(dto.description());

        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteCategory(String code) {
        Category category = categoryRepository.findCategoryByCodeIgnoreCase(code).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public Set<Category> findAllCategoies(List<String> code) {
        Set<Category> categories = categoryRepository.findCategoriesByCodeInIgnoreCase(code);

        if ((categories.size() != code.size()) || categories.isEmpty()) {
            throw new ResourceNotFoundException("Categories not found");
        }
        return categories;
    }

    @Override
    public Map<Long, Set<CategoryListResponseDTO>> findCategoryMap(Set<Long> bookIdSet) {
        Map<Long, Set<CategoryListResponseDTO>> categoriesMap = new HashMap<>();
        Set<CategoryListResponseDTO> categoriesDTO = null;

        Set<CategoryQueryDTO> categoriesQuery = categoryRepository.findCategoryByBookId(bookIdSet);
        for(CategoryQueryDTO c : categoriesQuery){
            if (!categoriesMap.containsKey(c.bookId())) {
                categoriesDTO = new HashSet<>();
            } else {
                categoriesDTO = categoriesMap.get(c.bookId());
            }

            categoriesDTO.add(new CategoryListResponseDTO(c.categoryCode(), c.categoryName()));
            categoriesMap.put(c.bookId(), categoriesDTO);
        }

        return categoriesMap;
    }
}
