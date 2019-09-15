package com.wp.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class SaveUser
 */
@WebServlet("/SaveUser")
public class SaveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement psRegister;

	public void init() {
		try {
			Class.forName(getServletContext().getInitParameter("driver"));
			con = DriverManager.getConnection(getServletContext().getInitParameter("url"),
					getServletContext().getInitParameter("user"), getServletContext().getInitParameter("pwd"));
			String sql = "insert into users values(?,?,?,?,?,?)";
			psRegister = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// reads-request
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String encryptedPassword = AESEncryption.encrypt(password);
		String username = request.getParameter("username");
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
			request.getRequestDispatcher("registration.jsp").include(request, response);
		} else {
			// process
			try {
				psRegister.setString(1, userid);
				psRegister.setString(2, encryptedPassword);
				psRegister.setString(3, username);
				psRegister.setString(4, address);
				psRegister.setString(5, mobile);
				psRegister.setString(6, email);
				int n = psRegister.executeUpdate();
				RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
				if (n != 0) {
					out.println("Registration Completed");
					rd.include(request, response);
				}

				else {
					out.println("Error, TRY AGAIN");
					rd.include(request, response);
				}

			} catch (Exception e) {
				out.println(e);
			}

		}
	}

}
