<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tester Main Page</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<label>Username : "${user.getName()}"</label><br>
	<label>Email : "${user.getEmailId()}"</label><br><hr>
	<c:choose>
		<c:when test="${ projects.size()>0}">
			<c:forEach items="${projects}" var="project"> 
				<table>
					<tr>
						<th>Projectid</th>
						<th>Name</th>
						<th>Description</th>
						<th>Status</th></tr>
					<tr>
					<td>${project.getProjectId()}</td>
					<td>${project.getProjectName()}</td>
					<td>${project.getDescription()}</td>
					<td>${project.getStatus()}</td>			
					</tr>
				</table>
				<h3>Bugs : </h3>
			<c:forEach items="${dict.get('project')}" var="bug"> 
				<table>
					<tr>
						<th>bugid</th>
						<th>Title</th>
						<th>Description</th>
						<th>status</th>
						<th>Created By</th>
						<th>Severity</th>
					</tr>
					<tr>
						<td>${bug.getUniqueId()}</td>
						<td>${bug.getName()}</td>
						<td>${bug.getDescription()}</td>
						<td>${bug.getStatus()}</td>
						<td>${bug.getCreatedBy()}</td>
						<td>${bug.getSeverityLevel()}</td>			
					</tr>
				</table>
			</c:forEach>
			</c:forEach>
		</c:when>
	
	<c:otherwise>{
		<h3>${message}</h3>	
	</c:otherwise>	
	</c:choose>
	<a href='testermain?operation=reportbug'><button>Create new bug</button></a><br>
	
</body>
</html>