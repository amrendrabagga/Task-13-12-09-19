<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.ResultSet" %>

<% 
	String uname = (String)session.getAttribute("uname");
	List<String> cartList = (List<String>)session.getAttribute("cart");
	ServletContext context = pageContext.getServletContext();
	Connection con = (Connection)context.getAttribute("dbConnection");
	PreparedStatement psCart = con.prepareStatement("select title,subject,price from books where book_id=?");
	String title,subject,price;
	ResultSet resultSet;
%>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>
<body>
<% if(cartList!=null && !cartList.isEmpty()){%>
<h1 align="center"><%=uname%> Cart</h1>
<hr/>

<table border="2" cellspacing="0" cellpadding="3px" align="center">
<tr>
	<th>
	TITLE
	</th>
	<th>
	SUBJECT
	</th>
	<th>
	PRICE
	</th>
	
</tr>
<% 
	for(String code: cartList){
		psCart.setString(1,code);
		resultSet = psCart.executeQuery();
		resultSet.next();//to move pointer to first record
		title = resultSet.getString(1);
		subject = resultSet.getString(2);
		price = resultSet.getString(3);
		//out.println(code);
%>
<tr>
<td><%=title%></td>
<td><%=subject%></td>
<td><%=price%></td>
<td><a href="RemoveFromCart?code=<%=code%>">Remove</a></td>
<% 
	}
}
else{
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Cart is empty');");	 
	out.println("</script>");	
	RequestDispatcher rd = request.getRequestDispatcher("buyerpage.jsp");
	rd.include(request,response);
}%>
	</tr>
</table>

</body>
</html>