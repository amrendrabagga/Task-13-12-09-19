package com.wp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.dao.BookDAO;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/UpdateBookController")
public class UpdateBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int book_id = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		String subject = request.getParameter("subject");
		int price = Integer.parseInt(request.getParameter("price"));
		
		BookDAO bookDAO = new BookDAO(getServletContext());
		int check = bookDAO.updateBook(book_id, title, subject, price);
		RequestDispatcher rd = request.getRequestDispatcher("AdminSearch.jsp");
		
		if(check!=0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Book Updated Successfully');");
			out.println("</script>");
			rd.include(request, response);
		}
		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Something went wrong, try again...');");
			out.println("</script>");
			rd.include(request, response);
		}
	}

}
