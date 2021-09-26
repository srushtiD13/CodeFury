<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign developer</title>
</head>
<body>
	<div align="center">
		<label>Bug Id : ${ bugid}</label><br/>
		<label>Project Id : ${ projectId}</label>
	</div>
	<div>
		<form action="projectdetails" method="POST">
			<label>Select developer to assign</label>
			<select name="developer">
				<c:forEach items="${ developers}" var="dev">
					<option value="${ dev}"> ${ dev}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Assign"></input>
			<input type="hidden" name="operation" value="assign"/>
	 	</form>
	</div>
</body>
</html>