<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Page</title>
</head>
<body align="center">

	<%@ include file="header.jsp"%>
	<%@ include file="userDetails.jsp"%>
	<hr>
	<a href="manager?operation=create">Create New Project</a>
	<hr>
	<table align="center">
			<!-- all projects under manager -->
			<%
			for (int i = 0; i < 10; i++) {
			%>
			<tr>
			<td><a href="projectdetails">Project <%=i%></a></td>
			</tr>
			<%
			}
			%>
			
		
	</table>
	<%@ include file="footer.jsp"%>

</body>
</html>