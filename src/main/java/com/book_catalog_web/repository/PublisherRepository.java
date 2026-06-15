package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    // ------- 1. Query By method Named --------------
    // SELECT * FROM publisher p WHERE p.name = publisherName
    public List<Publisher> findAllByName(String publisherName);

    // SELECT * FROM publisher p WHERE UPPER(p.name) LIKE UPPER(publisherName)
    public List<Publisher> findAllByNameLikeIgnoreCase(String publisherName);

    // ------- 2. Query By JPQL (Jakarta Persistense Query Language) ---> Sama seperti sql biasa
    //                           -- namun merujuk pada properti dan class entity, bukan kolom dan nama table
    @Query("SELECT p FROM Publisher p WHERE UPPER(p.name) LIKE UPPER(:publisherName) ")
    public List<Publisher> findByPublisherNameLikeIgnoreCase(String publisherName);


    // ------- 3. Query Native
    @Query(value = "SELECT p.id, p.name, p.description FROM publisher p WHERE UPPER(p.name) LIKE UPPER(:publisherName)",
    nativeQuery = true)
    public List<Publisher> findByPublisherNameUsingQueryNative(String publisherName);

    @Modifying
    @Query(value = "UPDATE Publisher p SET p.description=:description WHERE p.id=:id")
    public void updatePublisherDescription(String description, Long id);

    // SELECT * FROM publisher WHERE UPPER(name) LIKE UPPER(:publisherName)
    public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);

}
