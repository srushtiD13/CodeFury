<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="com.hsbc.entity.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Project</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<a href="manager">Manager Main</a>
	<div align="center">
	<h2>Add new Project</h2>
	<form action="addproject" method="POST">
	<div align ="center">Manager Id : 
		<input id='managerId' type="text" name="name" value="${ user.getUserId() }" placeholder="${ user.getUserId() }" disabled ><br><br />
		</div>
		<input id='projectName' type="text" name="name" value="${ project.name }" placeholder="Enter Name"><br/><br>
		
		<textarea id='projectDesc' type="text" name="description"	value="${ project.description }" placeholder="Enter description"></textarea>
		<br /> <br>
		<input type="date" onblur="validateDate()" name="startDate"
			id='startDate' placeholder="dd-mm-yyyy" value="${project.startDate }"
			max="2030-12-31"> <span id='dateerror'></span>
		<br> <br>
		<select	name="developers" id='developers' multiple="multiple">
			<option value="" disabled>Select Developers</option>
			<c:forEach items="${developers}" var="developer">
				<option value="${developer.getUserId()}" name="${developer.getName()}">
					${developer.getName()}</option>
			</c:forEach>
		</select> 
		<br><br>
	    <select name="testers">
			<option value="">Select Tester</option>
			<c:forEach items="${testers}" var="tester">
				<option value="${tester.getUserId()}" name="${tester.getName()}">
					${tester.getName()}</option>
			</c:forEach>
		</select><br><br>
		<input type="submit" value="Add" id='submitButton'>
		<span id='fieldserror'></span>
	</form>
	</div>
	<script>

	function validateDate()
	{	
		 var inputdate= document.getElementById('startDate').value;
	     var todayDate= new Date;
	     var startDate = new Date(inputdate);

	     millis= startDate - todayDate
	     var days = Math.ceil(millis / (60*60*24*1000))
	     console.log(days);
		 if(days-1<2)
		 {
		  document.getElementById('dateerror').innerHTML ='Start date must be 2 days later than today.';
		document.getElementById('submitButton').setAttribute('disabled', 'disabled');

		 }else{
			  document.getElementById('dateerror').innerHTML ='';

			 document.getElementById("submitButton").removeAttribute('disabled');

		 }

	}
</script>
</body>
</html>