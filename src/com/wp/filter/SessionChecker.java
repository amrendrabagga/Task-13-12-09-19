package com.wp.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionChecker
 */
//"/BookStore/*"
@WebFilter(urlPatterns = "/*",
initParams = { @WebInitParam(name = "excludeUrls", value = "/User,/index.jsp,/registration.jsp,/SaveUser"),
@WebInitParam(name = "excludeAdminUrls", value = "/buyerpage.jsp,/BookDetailsServlet,/BookListServlet,/CartManager,/RemoveFromCart,/UpdateUser,/ViewBook,/ViewBook.jsp,/ViewCart.jsp,/UpdateProfile.jsp,/ExploreStore.jsp")})
public class SessionChecker implements Filter {

	List<String> excludedURL;
	List<String> excludedAdminURL;

	/**
	 * Default constructor.
	 */
	public SessionChecker() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		if (!excludedURL.contains(path)) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			if (session != null && session.getAttribute("userid") != null) {
				//check user whether admin and buyer
				//such that either one can not browse other one pages
				String userid = (String)session.getAttribute("userid");
				if(userid.equals("000")) {
					if(!excludedAdminURL.contains(path))
						chain.doFilter(request, response);
					else
						request.getRequestDispatcher("adminpage.jsp").forward(request, response);
				}
				else {
					if(excludedAdminURL.contains(path)||path.equals("/Logout"))
						chain.doFilter(request, response);
					else
						request.getRequestDispatcher("buyerpage.jsp").forward(request, response);
				}
					
			} else {
				rd.forward(request, response);
			}
		} else
			chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String url = fConfig.getInitParameter("excludeUrls");
		String buyerURL = fConfig.getInitParameter("excludeAdminUrls");
		excludedURL = Arrays.asList(url.split(","));
		excludedAdminURL = Arrays.asList(buyerURL.split(","));
		

	}

}
