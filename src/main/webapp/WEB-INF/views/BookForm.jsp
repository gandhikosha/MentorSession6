<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="save" method="post">
<table>
<tr>
<td>Title</td><td><input type="text" value="${book.name}" name="name"/> </td></tr>
<tr>
<td>Author</td><td><input type="text" value="${book.author}" name="author"/> </td>
</tr>
<tr>
<td>Category</td><td><input type="text" value="${book.category}" name="category"/> </td>
</tr>
<tr>
<td colspan="2"><input type="hidden" value='${book.id}' name="id"/><input type="submit"/></td></tr>
</table>

</form>
</body>
</html>