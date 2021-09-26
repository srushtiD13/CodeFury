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
	<%@ include file="userDetails.jsp"%> <!-- Pass user object through controller -->
	<hr>
	<a href="addproject?user_id=${user.getEmployeeId()}">Create New Project</a>
	<hr>
	<table align="center">
			<!-- all projects under manager -->
			<c:forEach items="${projectList}" var="project">
			<tr>
			<td><a href="projectdetails?projectId=${project.getProjectId()}">${project.getProjectName()}</a></td>
			</tr>
			</c:forEach>
			
		
	</table>
	<%@ include file="footer.jsp"%>

</body>
</html>