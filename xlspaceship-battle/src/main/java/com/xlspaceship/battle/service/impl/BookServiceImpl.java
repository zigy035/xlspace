package com.xlspaceship.battle.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xlspaceship.battle.dao.BookDAO;
import com.xlspaceship.battle.model.Book;
import com.xlspaceship.battle.service.BookService;

@Transactional
public class BookServiceImpl implements BookService {
	
	private BookDAO bookDAO;
	
	@Override
	public List<Book> getBooks() {
		return bookDAO.getBooks();
	}

	@Override
	public Book getBook(Long bookId) {
		return bookDAO.getBook(bookId);
	}

	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	@Override
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		bookDAO.deleteBook(bookId);
	}

	// Inject DAO
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
}
