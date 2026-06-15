package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.Publisher;
import com.book_catalog_web.dto.PublisherCreateRequestDTO;
import com.book_catalog_web.dto.PublisherDetailResponseDTO;
import com.book_catalog_web.dto.PublisherUpdateRequestDTO;
import com.book_catalog_web.dto.response.PublisherListResponseDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.repository.PublisherRepository;
import com.book_catalog_web.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResultPageResponseDTO<PublisherListResponseDTO> findAllPublisher(Integer pages, Integer limit, String sortBy, String direction, String publisherName) {
        publisherName = StringUtils.isBlank(publisherName) ? "%" : "%" + publisherName + "%";
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        Page<Publisher> publisherPage = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
        List<PublisherListResponseDTO> dtos = publisherPage.stream().map(p -> {
            return new PublisherListResponseDTO(p.getId(), p.getName());
        }).toList();

        return new ResultPageResponseDTO<>(dtos, publisherPage.getTotalPages(), publisherPage.getTotalElements());
    }
}
