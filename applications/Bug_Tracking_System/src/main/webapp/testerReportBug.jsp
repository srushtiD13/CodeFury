<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new Bug</title>
</head>
<body>
	<!-- header jsp file  -->
	<jsp:include page="header.jsp"/>
	<hr>
	<div align="center" style="display:flex; margin-left:30%;margin-right: 30% ">
	<div style="flex-grow: 2 ">
			<label>Project Name</label><br><br>
			<label>Title </label><br><br>
			<label>Description </label><br><br><br>
			<label>Severity of Bug</label><br><br>
	</div>
	<div class="form-group" style="flex-grow: 1">
		<!-- projectdetails is the url pattern of controller -->
		<form action="testermain" method="POST">							
			<!-- this project name will come from previous page(main page of tester) -->
			<select name="projectname">
				<c:forEach items="${ projects}" var="project">
					<option value="${ project.getProjectName()}"> ${ project.getProjectName()}</option>
				</c:forEach>
			</select><br><br>
			<!--  <input type="text" name="projectname"  value="${ project.getProjectName()}" readonly="readonly"></input><br/><br>	-->
			<input type="text" name="title" placeholder="Title for bug"></input><br><br>
			<textarea type="text" name="description" placeholder="Description for bug" rows="2" cols="20"></textarea><br><br>
			<!-- <input type="text" name="severity" placeholder="severity of bug"></input> -->
			<select name="severity">
				<c:forEach items="${ severity}" var="severity">
					<option value="${ severity}"> ${ severity}</option>
				</c:forEach>
			</select><br><br>
			<input type="submit" value="Create Bug" style="text-align:center; margin-right: 200px"></input><br><br>
			<input type="hidden" name="operation" value="createbug"/>
		</form>
	</div>
	</div>
	<!-- footer jsp file -->
</body>
</html>