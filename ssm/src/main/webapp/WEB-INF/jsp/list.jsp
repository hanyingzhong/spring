<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.soecode.lyf.entity.Book"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

<body>
	<h2>All Employees in System</h2>

	<table border="1">
		<tr>
			<th>Employee Id</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<%
			request.setCharacterEncoding("UTF-8");
			List<Book> list = (List<Book>) request.getAttribute("list");
			for (int i = 0; i < list.size(); i++) {
				Book book = (Book) list.get(i);
		%>
		<tr>
			<td><%=book.getBookId()%></td>
			<td><%=book.getName()%></td>
			<td><%=book.getNumber()%></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>