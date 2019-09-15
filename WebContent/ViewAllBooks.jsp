<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.wp.entity.Book" %>
<%
	List<Book> list = (List<Book>)request.getAttribute("bookList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
</head>
<body>
<h2 align="center">BOOKS</h2><hr>
<table align="center" cellspacing="0" cellpadding="7px" border="1px">
<tr>
	<th>BookId</th>
	<th>Title</th>
	<th>Subject</th>
	<th>Price</th>
</tr>
<%for(Book book : list){ %>
<tr>
	<td><%=book.getBook_id() %></td>
	<td><%=book.getTitle() %></td>
	<td><%=book.getSubject() %></td>
	<td><%=book.getPrice() %></td>
</tr>
<%} %>
</table>
<hr>
<a href="adminpage.jsp">Go to DashBoard</a>
</body>
</html>