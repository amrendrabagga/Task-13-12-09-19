<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bookInfo" class="com.wp.entity.Book" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOOK UPDATION</title>
</head>
<body>
<h1>UPDATE BOOK</h1><hr>
<form action="UpdateBookController" method="post">
<table align="center" cellspacing="0" cellpadding="9px">
<tr>
<td>Title</td>
<td><input type="text" name="title" value='<jsp:getProperty property="title" name="bookInfo"/>'></td>
</tr>
<tr>
<td>Subject</td>
<td><input type="text" name="subject" value='${bookInfo.subject}'></td>
</tr>
<tr>
<td>Price</td>
<td><input type="text" name="price" value='${bookInfo.price}'></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="SUBMIT"></td>
</tr>
</table>
<input type="hidden" name="bookId" value=${bookInfo.book_id}>
</form>

</body>

</html>