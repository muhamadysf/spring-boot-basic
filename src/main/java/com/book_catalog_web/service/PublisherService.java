package com.book_catalog_web.service;

import com.book_catalog_web.dto.PublisherCreateRequestDTO;
import com.book_catalog_web.dto.PublisherDetailResponseDTO;
import com.book_catalog_web.dto.PublisherUpdateRequestDTO;

public interface PublisherService {

    public void createPublisher(PublisherCreateRequestDTO dto);
    public PublisherDetailResponseDTO findPublisherDetail(Long id);
    public void updatePublisher(Long id, PublisherUpdateRequestDTO dto);
    public void deletePublisher(Long id);
}
