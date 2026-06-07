package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Publisher;
import com.book_catalog_web.dto.PublisherCreateRequestDTO;
import com.book_catalog_web.dto.PublisherDetailResponseDTO;
import com.book_catalog_web.dto.PublisherUpdateRequestDTO;
import com.book_catalog_web.repository.PublisherRepository;
import com.book_catalog_web.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class    PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;


    @Override
    public void createPublisher(PublisherCreateRequestDTO dto) {

        Publisher publisher = new Publisher();
        publisher.setName(dto.name());
        publisher.setDescription(dto.description());

        publisherRepository.save(publisher);
    }

    @Override
    public PublisherDetailResponseDTO findPublisherDetail(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("publisher id not found."));

        return new PublisherDetailResponseDTO(publisher.getId(), publisher.getName(), publisher.getDescription());
    }

    @Override
    public void updatePublisher(Long id, PublisherUpdateRequestDTO dto) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher id not found"));

        publisher.setName(dto.name());
        publisher.setDescription(dto.description());

        publisherRepository.save(publisher);
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
