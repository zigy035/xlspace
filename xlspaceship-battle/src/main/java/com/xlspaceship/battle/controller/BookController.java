package com.xlspaceship.battle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xlspaceship.battle.form.BookForm;
import com.xlspaceship.battle.model.Book;
import com.xlspaceship.battle.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = bookService.getBooks();
		return ResponseEntity.ok().body(books);
	}
	
	@RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable("bookId") String bookId) {
		Book book = bookService.getBook(Long.valueOf(bookId));
		return ResponseEntity.ok().body(book);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addBook(@RequestBody BookForm bookForm) {
		Book book = new Book();
		book.setTitle(bookForm.getTitle());
		book.setAuthor(bookForm.getAuthor());
		book.setCategory(Integer.valueOf(bookForm.getCategory()));
		bookService.addBook(book);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateBook(@RequestBody BookForm bookForm) {
		Book book = new Book();
		book.setBookId(Long.valueOf(bookForm.getBookId()));
		book.setTitle(bookForm.getTitle());
		book.setAuthor(bookForm.getAuthor());
		book.setCategory(Integer.valueOf(bookForm.getCategory()));
		bookService.updateBook(book);
	}
	
	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("bookId") String bookId) {
		bookService.deleteBook(Long.valueOf(bookId));
	}
	
}
