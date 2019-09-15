package com.wp.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wp.encrytion.AESEncryption;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement psUpdate;

	@Override
	public void init() throws ServletException {
		try {
			// taking connection object using context
			con = (Connection) getServletContext().getAttribute("dbConnection");
			psUpdate = con.prepareStatement(
					"update users set userid=?,password=?,uname=?,address=?,mobile=?,email=? where userid=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("UpdateProfile.jsp");
		
		String useridSession = (String) request.getSession().getAttribute("userid");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password").trim();
		String confPassword = request.getParameter("confPassword").trim();
		String uname = request.getParameter("uname");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");

		// server side validation
		String regexEmail = "^[a-zA-Z0-9_.-][a-zA-Z0-9_.-]*@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)+$";
		String regexMobile = "[7-9][0-9]{9}";
		Pattern patternEmail = Pattern.compile(regexEmail);
		Pattern patternMobile = Pattern.compile(regexMobile);
		Matcher matcherEmail = patternEmail.matcher(email);
		Matcher matcherMobile = patternMobile.matcher(mobile);
		
		
		if (!matcherEmail.matches() || !matcherMobile.matches()) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Enter correct email or mobile number');");
			out.println("</script>");
			rd.include(request, response);
		} else {
			try {
				
				if (password.equals(confPassword)) {
					String encryptedPassword = AESEncryption.encrypt(password);
					psUpdate.setString(1, userid);
					psUpdate.setString(2, encryptedPassword);
					psUpdate.setString(3, uname);
					psUpdate.setString(4, address);
					psUpdate.setString(5, mobile);
					psUpdate.setString(6, email);
					psUpdate.setString(7, useridSession);
					int check = psUpdate.executeUpdate();
					if (check != 0) {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Profile Updated Successfully');");
						out.println("</script>");
						rd.include(request, response);

					}
				} else {
					// error msg

					out.println("<script type=\"text/javascript\">");
					out.println("alert('Password Mismatch');");
					out.println("</script>");
					rd.include(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
