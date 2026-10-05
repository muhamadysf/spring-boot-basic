package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findCategoryByCodeIgnoreCase(String code);
    public Page<Category> findCategoriesByNameIgnoreCase(String name, Pageable pageable);
}
