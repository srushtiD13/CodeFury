<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KNOTBOT_Import</title>
<script src="importfile.js"></script>
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
<body>
<jsp:include page="header.jsp"/>

     <div class="container">
  <div class="center">
<label for="jsonfile">Select a JSON file: </label><br><br>
	<input type="file" onchange="onChange(event)" id="jsonfile" name="jsonfile" accept=".json,.JSON,.xml,.XML" required>
	<br><br>
<br/>

<a href="homePage.jsp"><button style="height:40px;width:200px">Go to Home Page</button></a>
      </div>
</div>

<br/><br/>

<div id="errorMsg" style="color:red"></div>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<jsp:include page="footer.jsp"/>
</body>
</html>