package com.wp.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerifyUser
 */
@WebServlet(urlPatterns = { "/User" }, initParams = { @WebInitParam(name = "admin_id", value = "admin"),
		@WebInitParam(name = "password", value = "groot") })
public class VerifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement psVerify;

	@Override
	public void init() throws ServletException {

		try {
			Class.forName(getServletContext().getInitParameter("driver"));
			con = DriverManager.getConnection(getServletContext().getInitParameter("url"),
					getServletContext().getInitParameter("user"), getServletContext().getInitParameter("pwd"));
			psVerify = con.prepareStatement("select uname from users where userid=? and password=?");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userid = request.getParameter("userid").trim();
		String password = request.getParameter("password").trim();
		String utype = request.getParameter("utype").trim();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		try {
			if (utype.equals("owner")) {
				String admin_id = getServletConfig().getInitParameter("admin_id");
				String adminPassword = getServletConfig().getInitParameter("password");
				if (userid.equals(admin_id) && password.equals(adminPassword)) {
					session.setAttribute("userid", "000");// user id for admin
					response.sendRedirect("adminpage.jsp");
				} else {
					out.println("INVALID CREDENTIALS FOR ADMIN");
					rd.include(request, response);
				}

			} else {

				psVerify.setString(1, userid);
				psVerify.setString(2, password);
				ResultSet rs = psVerify.executeQuery();
				if (rs.next()) {

					// save userid and name in sessions

					session.setAttribute("userid", userid);
					session.setAttribute("uname", rs.getString(1));
					// whether user want to save the password
					String choice = request.getParameter("save");
					if (choice != null) {

						Cookie c1 = new Cookie("id", userid);
						Cookie c2 = new Cookie("pw", password);

						c1.setMaxAge(60 * 60 * 24 * 7);
						c2.setMaxAge(60 * 60 * 24 * 7);

						response.addCookie(c1);
						response.addCookie(c2);

					}
					RequestDispatcher buyerHome = request.getRequestDispatcher("buyerpage.jsp");
					buyerHome.forward(request, response);

				} else {
					out.println("INVALID BUYER CREDENTIALS");
					rd.include(request, response);
				}
			}
		} catch (Exception e) {
			out.println(e);
		}
	}

	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
