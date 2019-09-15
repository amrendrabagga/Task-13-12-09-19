package com.wp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.dao.BookDAO;
import com.wp.entity.Book;

/**
 * Servlet implementation class ViewAllBooksController
 */
@WebServlet("/ViewAllBooksController")
public class ViewAllBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO(getServletContext());
		List<Book> list = bookDAO.getAllBooks();
		request.setAttribute("bookList", list);
		request.getRequestDispatcher("ViewAllBooks.jsp").forward(request, response);
	}

}
