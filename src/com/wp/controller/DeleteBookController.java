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
 * Servlet implementation class DeleteBookController
 */
@WebServlet("/DeleteBookController")
public class DeleteBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookController() {
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
		int bookId;
		try {
		bookId = Integer.parseInt(request.getParameter("bookId"));Book deletedBook = bookDAO.delete(bookId);
		request.setAttribute("bookInfo", deletedBook);
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

		doGet(request, response);
	}

}
