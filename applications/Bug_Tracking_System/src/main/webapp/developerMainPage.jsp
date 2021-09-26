<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Developer Main Page</title>
    <link rel="stylesheet" href="./developerhome.css">
    <script>

    </script>
</head>
<body>
<form action="developerController" method = "GET"></form>
<!--    <div class="nav"> -->
<!--        <h2>Bug Tacker System</h2> -->
<!--    </div> -->

<!--    <div class="header"> -->

<!--     <div class="h-r"> -->
<!--         Last Login Time: -->
<!--     </div> -->

<!--     <div class="h-l" > -->
<!--         <div> -->
<%--             Name:  ${user.name} --%>
<!--         </div> -->
<!--         <div> -->
<%--             E-mail:  ${user.email} --%>
<!--         </div> -->
<!--         <div> -->
<%--             User Id:  ${user.id} --%>
<!--         </div> -->
<!--    </div> -->
   
<!-- </div> -->
    <div class='p_details'>
       <div> <h3>Project Details</h3>
        <table>
	<tr>
		<th colspan="2">Project ID</th>
		<th colspan="2">Project Name</th>
		<th colspan="2">Project Descp</th>
		<th colspan="2">Status</th>
		<th colspan="2">Start Date</th>
	</tr>
	<c:forEach items="${projects}" var= "details">
	
		<tr>
		<td>${ details.getProjectId()} </td>
		<td>${ details.getProjectName()}</td>
		<td>${ details.getDescription()}</td>
		<td>${ details.getStatus()}</td>
		<td>${ details.getStartDate()}</td>
		</tr>
 		
	</c:forEach>
	</table>
	</div>
    </div>
    <div class='b_details'>
        <div><h3>Bug Details</h3>
        <table>
	<tr>
		<th colspan="2">Bug ID</th>
		<th colspan="2">Bug Name</th>
		
		<th colspan="2">Action</th>
		
	</tr>
	<c:forEach items="${bug}" var= "detl">
		
		<tr>
		
		<td>${ detl.getUniqueId()} </td>
		<td>${ detl.getBugName()}</td>
		
<%-- 		<td><input type="submit" value="Close Bug" id="${ detl.bugid}"></td> --%>
<%-- 			<td><input type ="submit" name="${detl.bugid}" value="Close Bug"> </td> --%>
			<td><a href= "closebug?bugid=${detl.getUniqueId()}&user_id=${user_id}">Close Bug</a>
		</tr>
		
 		
	</c:forEach>
	</table>
        </div>
       

    </div>
</body>
</html>