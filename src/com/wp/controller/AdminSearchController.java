package com.wp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.dao.BookDAO;
import com.wp.entity.Book;

/**
 * Servlet implementation class AdminSearch
 */
@WebServlet("/AdminSearchController")
public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titleOrCid = request.getParameter("titleorcid");
		List<Book> list = new BookDAO(getServletContext()).searchBook(titleOrCid);
		request.setAttribute("bookList", list);
		RequestDispatcher rd = request.getRequestDispatcher("AdminSearch.jsp");
		rd.forward(request, response);
	}

}
