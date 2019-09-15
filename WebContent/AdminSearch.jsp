<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List,com.wp.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">Search Book By Title or By Code</h3> 
<hr>
<form action="AdminSearchController" method="post">
<table align="center" cellpadding="3px">	
				<tr>
					<td>Enter Title or Code</td>
					<td><input type="text" name="titleorcid" /></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type="submit"
						value="SUBMIT" /></td>
				</tr>
			</table>
</form>
<% 
	List<Book> list = (List<Book>)request.getAttribute("bookList");	
	
	if(list!=null && !list.isEmpty()){
	%>
	<hr>
	<table align="center" cellpadding="5px" border="1" cellspacing="0"> 
	<tr>
		<th>
		BookId
		</th>
		<th>
		Title
		</th>
		<th>
		Subject
		</th>
		<th>
		Price
		</th>
	</tr>
	<%for(Book book:list){ %>
	<tr>
	<td><%=book.getBook_id()%></td>
	<td><%=book.getTitle()%></td>
	<td><%=book.getSubject()%></td>
	<td><%=book.getPrice()%></td>
	<td><a href="DeleteBookController?bookId=<%=book.getBook_id()%>">Remove</a></td>
	<td><a href="SearchBookController?bookId=<%=book.getBook_id()%>">Update</a></td>
	</tr>
	<%} %>
	</table>
<%} %>
<hr>
<a href="adminpage.jsp">Go to DashBoard</a></body>
</html>