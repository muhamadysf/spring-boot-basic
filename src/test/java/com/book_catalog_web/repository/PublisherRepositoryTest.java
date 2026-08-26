package com.book_catalog_web.repository;

import com.book_catalog_web.domain.Publisher;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

//    @Test
//    void testFindByName_Success() {
//        Publisher publisher = new Publisher();
//
//        publisher.setName("Apress");
//        publisher.setDescription("Apress adalah kowkokelwkdksldksdklsdkssfdfdsf");
//        publisherRepository.save(publisher);
//
//        List<Publisher> publishers = publisherRepository.findAllByName("Apress");
//        assertEquals("Apress",publishers.getFirst().getName());
//    }
//
//    @Test
//    void testFindByName_Inclompete() {
//        Publisher publisher = new Publisher();
//
//        publisher.setName("Apress");
//        publisher.setDescription("Apress adalah kowkokelwkdksldksdklsdkssfdfdsf");
//        publisherRepository.save(publisher);
//
//        List<Publisher> publishers = publisherRepository.findAllByName("Ap");
//        assertEquals(0,publishers.size());
//    }
//
//    @Test
//    void testFindByNameLikeIgnoreCase_Incomplete() {
//        Publisher publisher = new Publisher();
//
//        publisher.setName("Apress");
//        publisher.setDescription("sdsdkljkdkdfhjsdhfksafjdksf");
//        publisherRepository.save(publisher);
//
//        List<Publisher> publishers = publisherRepository.findAllByNameLikeIgnoreCase("ap%");
//        assertEquals(1, publishers.size());
//    }
//
//    @Test
//    void testFindByPublisherNameIgnoreCase(){
//        Publisher publisher = new Publisher();
//
//        publisher.setName("Appress");
//        publisher.setDescription("dsdasdfsajfhskdfjjkashfjas");
//        publisherRepository.save(publisher);
//
//        List<Publisher> publishers = publisherRepository.findByPublisherNameLikeIgnoreCase("ap%");
//        assertEquals(1, publishers.size());
//    }

//    @Test
//    void TestFindByNameUsingQueryNative() {
//        Publisher publisher = new Publisher();
//
//        publisher.setName("Appress");
//        publisher.setDescription("kdfjksdhfjsdhfjsfsdafsdfhasdj");
//        publisherRepository.save(publisher);
//
//        List<Publisher> publishers = publisherRepository.findByPublisherNameUsingQueryNative("ap%");
//        assertEquals(1, publishers.size());
//    }

//    @Test
//    void testUpdatePublisher_success(){
//        Publisher publisher = publisherRepository.findById(1L).orElseThrow(()-> new RuntimeException("publisher id not found"));
//
//        publisher.setDescription("modified description");
//        System.out.println("start process update publisher");
//
//        // save ---> tidak akan mempengaruhi perubahan ke db sebelum adanya perintah commit,
//        // saveAndFlush ---> otomatis melakukan perintah commit.
//        publisherRepository.saveAndFlush(publisher);
//        System.out.println("process is done.!");
//
//        Publisher publisherUpdate = publisherRepository.findById(1L).orElseThrow(() -> new RuntimeException("Id not found"));
//        assertEquals("modified description", publisherUpdate.getDescription());
//    }
//
//    @Test
//    void TestUpdatePublisherModifying_success(){
//        System.out.println("start process update using @Modifying");
//        publisherRepository.updatePublisherDescription("modified description", 1L);
//        System.out.println("process update with Modifying is done.!");
//
//        Publisher publisher = publisherRepository.findById(1L).orElseThrow(() -> new RuntimeException("publisher id not found"));
//        assertEquals("modified description", publisher.getDescription());
//    }
//
//    @Test
//    void testDeletePublisher_success(){
//        publisherRepository.deleteById(1L);
//
//        assertEquals(false, publisherRepository.existsById(1L));
//    }
//
//    @Test
//    void testDeletePublisher_failed(){
//        publisherRepository.deleteById(2L);
//
//        assertEquals(false, publisherRepository.existsById(2L));
//    }
}
