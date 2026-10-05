package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "publisher")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    public void addBook(Book book){
        this.books.add(book);
        book.setPublisher(this);
    }

    public void removeBook(Book book){
        book.setPublisher(null);
        this.books.remove(book);
    }

    public void removeBooks(){
        Iterator<Book> iterator = this.books.iterator();

        while(iterator.hasNext()){
            Book book = iterator.next();
            book.setPublisher(null);
            iterator.remove();
        }
    }
}
