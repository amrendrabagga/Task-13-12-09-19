<html>
<body>
	<h3>Online Book Store</h3>
	<h4>Registration-Form</h4>
	<hr>
	<div align="center">
	<form action="SaveUser" method="post">
	<table align="center" cellpadding="8px" style="font-size:16px;">
	<tr><td>Userid</td><td><input type="text" name="userid" required="required" pattern="[a-zA-z0-9]+"/></td></tr>		
	<tr><td>Password</td><td><input type="password" name="password" required="required"/></td></tr>
	<tr><td>Username</td><td><input type="text" name="username" required="required"/></td></tr>
	<tr><td>Address</td><td><input type="text" name="address" required="required"/></td></tr>
	<tr><td>Mobile</td><td><input type="text" name="mobile" required="required" pattern="[1-9]{1}[0-9]{9}"/></td></tr>
	<tr><td>Email-Id</td><td><input type="email" name="email" required="required"/></td></tr>	
	<tr align="center"><td colspan="2"><input type="submit" value="Register"/></td></tr>		
	</table>
	</form>
	</div>
	<hr>
	<a href="index.jsp">Home</a>
</body>
</html>