package com.wp.entity;

public class Book {
	private int book_id;
	private String title;
	private String subject;
	private int price;

	public Book() {
		super();
	}

	public Book(int book_id, String title, String subject, int price) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.subject = subject;
		this.price = price;
	}

	public Book(String title, String subject, int price) {
		super();
		this.title = title;
		this.subject = subject;
		this.price = price;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", subject=" + subject + ", price=" + price + "]";
	}

}
