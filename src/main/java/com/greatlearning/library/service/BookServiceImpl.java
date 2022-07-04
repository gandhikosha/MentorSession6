package com.greatlearning.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.library.entity.Book;
import com.greatlearning.library.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> findAll() {
		return bookRepo.findAll();
	}

	@Override
	public void save(Book book) {
		bookRepo.save(book);	
	}

	@Override
	public Book findById(Integer id) {
		return bookRepo.findById(id).get();
	}

	@Override
	public void delete(Book book) {
		bookRepo.delete(book);		
	}

	@Override
	public List<Book> findByName_Author(String name, String author) {
		return bookRepo.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
	}

}
