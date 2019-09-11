package com.wp.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		List<String> cartList = (List<String>)(session.getAttribute("cart"));
		//updating cart
		cartList.remove(code);
		session.setAttribute("cart", cartList);
		
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Cart Updated Successfully');");	 
		out.println("</script>");
		RequestDispatcher rd = request.getRequestDispatcher("ViewCart.jsp");
		rd.include(request, response);
		//response.sendRedirect("ViewCart.jsp");
	}

	

}
