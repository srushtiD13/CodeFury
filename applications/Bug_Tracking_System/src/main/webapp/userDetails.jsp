<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>Email Id : ${user.getEmailId()}</td>
		</tr>
		<tr>
			<td>Role : ${user.getRole()}</td>
		</tr>
			<td>Last login date & Time : ${user.getDoj()}</td>
		<tr>
		</tr>
	</table>
</body>
</html>