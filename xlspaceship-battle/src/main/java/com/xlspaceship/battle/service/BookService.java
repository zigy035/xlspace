package com.xlspaceship.battle.service;

import java.util.List;

import com.xlspaceship.battle.model.Book;

public interface BookService {
	
	List<Book> getBooks();
	
	Book getBook(Long bookId);
	
	void addBook(Book book);

	void updateBook(Book book);
	
	void deleteBook(Long bookId);
}
