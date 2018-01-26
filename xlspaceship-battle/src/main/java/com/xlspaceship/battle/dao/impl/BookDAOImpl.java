package com.xlspaceship.battle.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.xlspaceship.battle.dao.BookDAO;
import com.xlspaceship.battle.model.Book;

public class BookDAOImpl implements BookDAO {
	
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		return entityManager.createQuery("FROM Book b ").getResultList();
	}

	@Override
	public Book getBook(Long bookId) {
		try {
			return (Book) entityManager.createQuery("FROM Book b "
					+ "WHERE b.bookId = :bookId")
					.setParameter("bookId", bookId)
					.getSingleResult();
		} catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		}
		return null;
	}

	@Override
	public void addBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void updateBook(Book book) {
		entityManager.merge(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		Book book = getBook(bookId);
		Book mergedBook = entityManager.merge(book);
		if (mergedBook != null) {
			entityManager.remove(book);
		}
	}

}
