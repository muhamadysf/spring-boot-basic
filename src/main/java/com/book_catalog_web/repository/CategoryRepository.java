package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findCategoryByCodeIgnoreCase(String code);

    Page<Category> findAllByNameLikeIgnoreCase(String name, Pageable pageable);

    Set<Category> findCategoriesByCodeInIgnoreCase(List<String> codes);
    
}
