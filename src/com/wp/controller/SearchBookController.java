package com.wp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.dao.BookDAO;
import com.wp.entity.Book;

/**
 * Servlet implementation class SearchBookController
 */
@WebServlet("/SearchBookController")
public class SearchBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int book_id = Integer.parseInt(request.getParameter("bookId"));
		BookDAO bookDAO = new BookDAO(getServletContext());
		Book book = bookDAO.searchBook(book_id);
		
		request.setAttribute("bookInfo", book);
		request.getRequestDispatcher("UpdateBook.jsp").forward(request, response);
	}

	

}
