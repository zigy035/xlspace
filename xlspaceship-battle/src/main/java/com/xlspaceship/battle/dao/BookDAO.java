package com.xlspaceship.battle.dao;

import java.util.List;

import com.xlspaceship.battle.model.Book;

public interface BookDAO {
	
	List<Book> getBooks();
	
	Book getBook(Long bookId);
	
	void addBook(Book book);

	void updateBook(Book book);
	
	void deleteBook(Long bookId);
}
