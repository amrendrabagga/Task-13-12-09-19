<%
	//check if user has already logged in
	if(session.getAttribute("userid")!=null){
		String userid = (String)session.getAttribute("userid");
		//check whether user is admin or buyer
		if(!userid.equals("000"))//userid for admin is 000
			request.getRequestDispatcher("buyerpage.jsp").forward(request,response);
		else
			request.getRequestDispatcher("adminpage.jsp").forward(request,response);
	}
	String s1="",s2="";
	//to read the cookies
	//step-1 (fetch all the cookies coming with request)
		Cookie ck[]=request.getCookies();
	//step-2 (search for the desired one)
	if(ck!=null)
		for(Cookie c:ck){
			String name=c.getName();
			if(name.equals("id")){
				s1=c.getValue();
			}else if(name.equals("pw")){
				s2=c.getValue();
			}
		}
	
%>

<html>
<body>
	<h3>Online Book Store</h3>
	<hr>
	<form action="User" method="post">
	<pre>
		Userid			<input type="text" name="userid" value="<%=s1%>" required="required"/>
		Password		<input type="password" name="password" value="<%=s2%>" required="required"/>
		RememberMe		<input type="checkbox" name="save" value="yes" checked="checked" />
		Usertype 	owner	<input type="radio" name="utype" value="owner" />
				buyer 	<input type="radio" name="utype" value="buyer" checked="checked"/>
						<input type="submit" value="Login"/>
	</pre>
	</form>
	<hr>
	<a href="registration.jsp">New-User</a>
</body>
</html>