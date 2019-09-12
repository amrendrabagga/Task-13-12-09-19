<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.PreparedStatement,java.sql.ResultSet" %>

<%@ page import="java.util.Map,java.util.List" %>

<% 
	String uname = (String)session.getAttribute("uname");
	List<String> cartList = (List<String>)session.getAttribute("cart");
	Map<String,Integer> priceChecker = (Map<String,Integer>)session.getAttribute("updatedPrice");
	
	ServletContext context = pageContext.getServletContext();
	Connection con = (Connection)context.getAttribute("dbConnection");
	PreparedStatement psCart = con.prepareStatement("select * from books where book_id=?");
	String title,subject,price,bcode;
	ResultSet resultSet;
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>
<body>
<% if(cartList!=null && !cartList.isEmpty()){%>
<h1 align="center"><%=uname.toUpperCase()%> CART</h1>
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
		bcode = resultSet.getString(1);	
		title = resultSet.getString(2);
		subject = resultSet.getString(3);
		price = resultSet.getString(4);
		
		 if(priceChecker!=null && priceChecker.containsKey(bcode)){
			price = ""+priceChecker.get(code);
		}
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
	/* out.println("<script type=\"text/javascript\">");
	out.println("alert('Cart is empty');");	 
	out.println("</script>");	 */
	out.print("<h5>Your cart is empty</h5>");
}%>
</tr>
</table>
<hr>
<a href="buyerpage.jsp">BuyerPage</a>
</body>
</html>