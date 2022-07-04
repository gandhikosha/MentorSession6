package com.greatlearning.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);

}