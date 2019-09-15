package com.wp.dao;

import java.util.List;

import com.wp.entity.Book;

public interface DAO {

	Book save(Book book);

	Book delete(int book_id);

	List<Book> getAllBooks();
	
	List<Book> searchBook(String titleorcid);
	
	Book searchBook(int book_id);
	
	int updateBook(int book_id,String title,String subject,int price);
}
