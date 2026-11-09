package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Category;
import com.book_catalog_web.dto.query.CategoryQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findCategoryByCodeIgnoreCase(String code);

    Page<Category> findAllByNameLikeIgnoreCase(String name, Pageable pageable);

    Set<Category> findCategoriesByCodeInIgnoreCase(List<String> codes);

    @Query("""
SELECT new com.book_catalog_web.dto.query.CategoryQueryDTO(b.id, bc.code, bc.name) FROM Book b JOIN b.categories bc WHERE b.id IN :bookIdSet
""")
    public Set<CategoryQueryDTO> findCategoryByBookId(Set<Long> bookIdSet);
}
