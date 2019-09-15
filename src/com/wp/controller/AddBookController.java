package com.wp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.dao.BookDAO;
import com.wp.entity.Book;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet("/AddBookController")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 
		BookDAO bookDAO = new BookDAO(getServletContext());
		try {
		String bookTitle = request.getParameter("bookTitle");
		String bookSubject = request.getParameter("bookSubject");
		int bookPrice = Integer.parseInt(request.getParameter("price"));
		Book book = new Book(bookTitle, bookSubject, bookPrice);
		Book addedBook = bookDAO.save(book);
		request.setAttribute("bookInfo", addedBook);
		request.getRequestDispatcher("BookDetails.jsp").forward(request, response);
		}
		catch(NumberFormatException e) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Enter correct book id');");
			out.println("</script>");
			request.getRequestDispatcher("DeleteBook.jsp").include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
