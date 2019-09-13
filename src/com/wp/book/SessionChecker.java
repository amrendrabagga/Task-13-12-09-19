package com.wp.book;

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
@WebFilter(urlPatterns = "/*", initParams = @WebInitParam(name = "excludeUrls", value = "/User,/index.jsp,/registration.jsp"))
public class SessionChecker implements Filter {

	List<String> excludedURL;

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
				chain.doFilter(request, response);
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
		excludedURL = Arrays.asList(url.split(","));

	}

}
