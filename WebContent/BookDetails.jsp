<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bookInfo" class="com.wp.entity.Book" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookDetail</title>
</head>
<body>
<h3 align="center">Book Details</h3><hr>
<table border="1" cellspacing="0" cellpadding="3px" align="center">
				<tr>
					<td>Title</td>
					<td><jsp:getProperty property="title" name="bookInfo"/>
				</tr>
				<tr>
					<td>Subject Name</td>
					<td><jsp:getProperty property="subject" name="bookInfo"/>
				</tr>
				<tr>
					<td>Price</td>
					<td><jsp:getProperty property="price" name="bookInfo"/>
				</tr>
</table>
<hr>
<a href="adminpage.jsp">AdminPage</a>			
</body>

</html>