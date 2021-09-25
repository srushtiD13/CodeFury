<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KNOTBOT_BugTrackingSystem</title>
<style>
.container { 
  height: 200px;
  position: relative;
  
}

.center {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>
</head>
<body  >
<jsp:include page="header.jsp"/>

     <div class="container">
  <div class="center">
      <a href="import.jsp"><button style="height:40px;width:200px;text-align:center">Import User</button></a>
      <br/><br/>
      <a href="register.jsp"><button style="height:40px;width:200px">Register</button></a>
      <br/><br/>
      <a href="login.jsp"><button style="height:40px;width:200px">Login</button></a>
      <br/><br/>
      </div>
</div>
<br/><br/>
<jsp:include page="footer.jsp"/>
</body>
</html>