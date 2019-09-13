package com.wp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		// TODO Auto-generated method stub
		return null;
	}

}
