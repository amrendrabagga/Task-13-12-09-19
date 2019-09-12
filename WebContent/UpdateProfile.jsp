<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<%
	String userid = (String)session.getAttribute("userid");
	Connection con = (Connection)application.getAttribute("dbConnection");
	PreparedStatement psUser = con.prepareStatement("select *from users where userid=?");
	psUser.setString(1,userid);
	ResultSet rsUser = psUser.executeQuery();
	rsUser.next();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<h1>Welcome <%=((String)session.getAttribute("uname")).toUpperCase()%></h1>
<hr>
<form action="UpdateUser" method="post">
<table align="center" style="font-size:18px;" cellpadding="8px">
<tr>
<td>UserID</td>
<td><input type="text" name="userid" required="required" pattern="[a-zA-Z]+" value=<%=rsUser.getString(1) %>></td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" name="password" required="required" pattern="[a-zA-Z0-9]+" value=<%=rsUser.getString(2)%>></td>
</tr>
<tr>
<td>ConfirmPassword</td>
<td><input type="password" name="confPassword" required="required" pattern="[a-zA-Z0-9]+"></td>
</tr>
<tr>
<td>Name</td>
<td><input type="text" name="uname" required="required" pattern="[a-zA-Z0-9]+" value=<%=rsUser.getString(3) %>></td>
</tr>
<tr>
<td>Address</td>
<td><textarea rows="3" cols="18" name="address" ><%=rsUser.getString(4) %></textarea></td>
</tr>
<tr>
<tr>
<td>Mobile</td>
<td><input type="text" name="mobile" required="required" pattern="[1-9]{1}[0-9]{9}" value=<%=rsUser.getString(5) %>></td>
</tr>
<tr>
<td>Email</td>
<td><input type="email" name="email" required="required" value=<%=rsUser.getString(6) %>></td>
</tr>
<tr align="center">
<td colspan="2"><input type="submit" value="SUBMIT"/></td>
</tr>

</table>
</form>
</body>
</html>