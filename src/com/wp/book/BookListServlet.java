package com.wp.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String[] subjects = request.getParameterValues("checkedSubject");

			String append = "";
			if (subjects != null) {

				for (String subject : subjects) {
					append += "'" + subject + "',";
				}
				append = append.substring(0, append.length() - 1);
				Cookie subPreferenceCookie = new Cookie("subjectOffer", String.join(",", subjects));
				subPreferenceCookie.setMaxAge(120);// for 1 min
				response.addCookie(subPreferenceCookie);
			}
			Connection con = (Connection) getServletContext().getAttribute("dbConnection");
			String sql = "select book_id,title from books";
			if (subjects != null)
				sql = "select book_id,title from books where subject in(" + append + ")";
			PreparedStatement psBookList = con.prepareStatement(sql);

			ResultSet rs = psBookList.executeQuery();
			out.println("<html>");
			out.println("<html><body>");
			out.println("<h3>Select The Desired Title</h3>");
			out.println("<hr>");
			while (rs.next()) {
				String code = rs.getString(1);
				String title = rs.getString(2);

				out.println("<a href=BookDetailsServlet?code=" + code + ">");
				out.println(title);
				out.println("</a><br>");
			}
			out.println("<hr>");
			out.println("<a href='ExploreStore.jsp'>Subject-Page</a><br>");
			out.println("<a href=buyerpage.jsp>Buyer-Page</a>");
			out.println("</body></html>");

		} catch (Exception e) {
			out.println(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
