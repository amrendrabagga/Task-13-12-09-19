<!DOCTYPE html>
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


<html>
<head>
<meta charset="UTF-8">
<title>STORE</title>
</head>
<body>
<marquee><h4>Attractive Offers on <%=offer%></h4></marquee>
<h3 align="center">Select The Desired Subject</h3>
<hr>
<form action="BookListServlet" method="post">
<table align="center" cellpadding="3px">
<%while(resultSet.next()){ %>
<tr>
<td>
<%=resultSet.getString(1).toUpperCase()%>
</td>
<td><input type="checkbox" name="checkedSubject" value='<%=resultSet.getString(1)%>'><br>
</td>

</tr>
<%} %>
<br>
<tr align="center">
<td colspan="2"><input type="submit" value="SUBMIT"/></td>
</tr>
</table>
</form>
<hr>
<a href="buyerpage.jsp">BuyerPage</a>
</body>
</html>