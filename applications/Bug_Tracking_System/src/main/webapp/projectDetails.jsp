<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Details</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div style="margin:40px;">
	<span>
		<%-- <label style="text-align:left;display:block;float:left">Project name : ${ project.getProjectName()}</label>
		<label style="text-align:right;display:block;float:right">Manager name : ${ project.getProjectManager()}</label><br/>
		<label style="text-align:right;float:right">Employee Id 1 </label> --%>
		
		<span>
			<h1 style="text-align:center">Project Details</h1>
			<table align="center" style="border:1px solid;width:50%">
				<tr>
					<th>Name of Project :</th>
					<td>${ project.getProjectName()}</td>	
				</tr>
				<tr>
					<th>Project Id : </th>
					<td>${ project.getProjectId()}</td>
				</tr>
				<tr>
					<th>Start Date : </th>
					<td>${ project.getStartDate()}</td>
				</tr>
				<tr>
					<th>Id of Project Manager : </th>
					<td>${ project.getProjectManagerId()}</td>
				</tr>
				<tr>
					<th rowspan='10'>Team members : </th>
					<!-- <td>${ employee.getTeam()}</td>-->
				</tr>
				
				<c:forEach items="${ developers}" var="user">
					<tr><td>${ user.getEmployeeName()}</td></tr>
				</c:forEach>
				
			</table>
		</span>
		<br/>
		<hr/>
		<span style="text-align:center">
			<h1>Bug Details</h1>
			<div align="left">
				<label>Filter by Bug Name</label>
				<a href="projectdetails?operation=filter&projectId=${ project.getProjectId()}">Filter</a>
			</div>
			<table align="center" border="1|1" style="border-collapse:collapse;padding: 30px; margin:30 px;width:50%">
				<tr>
					<th>Bug Id</th>
					<th>Bug name</th>
					<th>Marked to Close</th>
					<th>Status</th>
					<th>Close</th>
					<th>Assign</th>
				</tr>
				<c:forEach items="${ bug}" var="bug">
					<tr>
						<td>${ bug.getUniqueId()}</td>
						<td>${ bug.getBugName()}</td>
						<td>
						<c:choose>
						<c:when test="${ bug.getMarkedForClosing()=='yes'}">
							Close the bug 
						</c:when>
						<c:when test="${ bug.getMarkedForClosing()=='closed'}">
							Bug closed
						</c:when>
						<c:otherwise>
							Not marked for closing
						</c:otherwise>
						</c:choose>
						</td>
						<td>
							<c:choose>
							<c:when test="${ bug.getStatus()=='open'}">
								Bug is open
							</c:when>
							<c:otherwise>
								Bug is closed
							</c:otherwise>
							</c:choose>	
						</td>
						<td><a href="projectdetails?operation=close&projectId=${ project.getProjectId()}&bugId=${ bug.getUniqueId()}">Close</a></td>
						<td><!-- a href="projectdetails?operation=assign&projectId=${ project.getProjectId()}&bugId=${ bug.getUniqueId()}">Assign To</a>   -->
						<div>
							<form action="projectdetails?bugId=${ bug.getUniqueId()}" method="POST">
								<label>Select developer to assign</label>
								<select name="developer">
									<c:forEach items="${ developers}" var="dev">
										<option value="${ dev.getEmployeeId()}"> ${ dev.getEmployeeName()}</option>
									</c:forEach>
								</select>
								<input type="submit" value="Assign"></input>
								<input type="hidden" name="operation" value="assign"/>
	 						</form>
						</div>
						
						</td>				<!-- pass bugid  -->
					</tr>
				</c:forEach>
			</table>
		</span>
	</span>
	</div>
</body>
</html>