package com.wp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.wp.connector.DatabaseConnection;
import com.wp.entity.Book;

public class BookDAO implements DAO {

	private ServletContext context;
	private Connection con;

	public BookDAO(ServletContext context) {
		this.context = context;
		con = DatabaseConnection.getDatabaseConnection(context);
	}

	@Override
	public Book save(Book book) {
		try {
			PreparedStatement psSave = con.prepareStatement("insert into books(title,subject,price) values(?,?,?)");
			psSave.setString(1, book.getTitle());
			psSave.setString(2, book.getSubject());
			psSave.setInt(3, book.getPrice());
			int n = psSave.executeUpdate();
			if (n != 0) {
				System.out.println(n + "rows inserted successfully");
				return book;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book delete(int book_id) {
		try {
			PreparedStatement psFetch = con.prepareStatement("select *from books where book_id=?");
			psFetch.setInt(1, book_id);
			ResultSet rs = psFetch.executeQuery();
			if (rs.next()) {
				PreparedStatement psDelete = con.prepareStatement("delete from books where book_id=?");
				psDelete.setInt(1, book_id);
				int check = psDelete.executeUpdate();
				System.out.println(check + " row deleted");
				if (check != 0) {
					Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
					return book;
				} else// if delete operation fails
					return null;
			} else// if book doesnt exist
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> list = new ArrayList<>();
		try {
			Statement psFetch = con.createStatement();
			ResultSet rsFetch = psFetch.executeQuery("select *from books");
			while(rsFetch.next()) {
				Book book = new Book();
				book.setBook_id(rsFetch.getInt(1));
				book.setTitle(rsFetch.getString(2));
				book.setSubject(rsFetch.getString(3));
				book.setPrice(rsFetch.getInt(4));
				list.add(book);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> searchBook(String titleorcid) {
		List<Book> list = new ArrayList<Book>();
		try {
		PreparedStatement psSearch = con.prepareStatement("select *from books where book_id like '"+titleorcid+"%' " +"or title like '"+titleorcid+"%'" );
		ResultSet rsSearch = psSearch.executeQuery();
		while(rsSearch.next()) {
			
			Book book = new Book();
			book.setBook_id(rsSearch.getInt(1));
			book.setTitle(rsSearch.getString(2));
			book.setSubject(rsSearch.getString(3));
			book.setPrice(rsSearch.getInt(4));
			list.add(book);
		}
		
		return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Book searchBook(int book_id) {
		Book book=null;
		try {
		PreparedStatement psSearch = con.prepareStatement("select * from books where book_id=?");
		psSearch.setInt(1, book_id);
		ResultSet rsSearch = psSearch.executeQuery();
		if(rsSearch.next()) {
			book=new Book();
			book.setBook_id(rsSearch.getInt(1));
			book.setTitle(rsSearch.getString(2));
			book.setSubject(rsSearch.getString(3));
			book.setPrice(rsSearch.getInt(4));
			return book;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return book;
	}

	@Override
	public int updateBook(int book_id, String title, String subject, int price) {
		try {
		PreparedStatement psUpdate = con.prepareStatement("update books set title=?,subject=?,price=? where book_id=?");
		psUpdate.setString(1, title);
		psUpdate.setString(2, subject);
		psUpdate.setInt(3, price);
		psUpdate.setInt(4, book_id);
		int check = psUpdate.executeUpdate();
		return check;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
