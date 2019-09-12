<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.Connection,java.sql.PreparedStatement,java.sql.ResultSet" %>
<% 
	Connection con = (Connection)application.getAttribute("dbConnection");
	PreparedStatement psSubjects = con.prepareStatement("select distinct subject from books");
	ResultSet resultSet = psSubjects.executeQuery();
	Cookie[] cookies = request.getCookies();
	String offer = "All Books";
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (cookieName.equals("subjectOffer"))
				offer = cookie.getValue() + " books";
		}
	}
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>STORE</title>
</head>
<body>
<marquee><h4>Attractive Offers on <%=offer%></h4></marquee>
<h3>Select The Desired Subject</h3>
<hr>
<form action="BookListServlet" method="post">
<%while(resultSet.next()){ %>
<%=resultSet.getString(1).toUpperCase()%><input type="checkbox" name="checkedSubject" value='<%=resultSet.getString(1)%>'><br>
<%} %>
<br>
<input type="submit" value="SUBMIT"/>
</form>
<hr>
<a href="buyerpage.jsp">BuyerPage</a>
</body>
</html>