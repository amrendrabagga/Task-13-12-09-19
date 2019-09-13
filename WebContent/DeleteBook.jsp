<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">Delete Book</h3>
<hr>

<form action="DeleteBookController" method="post">
<table align="center" cellpadding="3px">
				<tr>
					<td>BookID</td>
					<td><input type="text" name="bookId" />
				</tr>
				<tr>
					<td colspan=2 align="center"><input type="submit"
						value="SUBMIT" /></td>
				</tr>
				
</table>				
</form>
<hr>
<a href="adminpage.jsp">Go to Dashboard</a>
</body>
</html>