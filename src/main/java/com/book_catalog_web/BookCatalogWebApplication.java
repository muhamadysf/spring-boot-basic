package com.book_catalog_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BookCatalogWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogWebApplication.class, args);
	}

}
