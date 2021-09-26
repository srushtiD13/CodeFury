<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KNOTBOT_Login</title>
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
<script>
function checkValidEmail ()
{
	//email will be a string passed to validate
	email = document.getElementById("email").value;
	if(email)//ifemail is not NULL/empty
	{
		var regex=/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
		if(regex.test(email))
		{
			//EMAIL IS VALID
			//SEND TO DB
			console.log("correct email");
			return true;
			
		}
		else
		{
			//email is invalid..it can have alphabets/nums/@/.
			//hence print error msg
			console.log("wrong email");
			return false;
		}
	}
	else
	{
		//email is empty/null
		console.log("empty email");
		return false;
	}
}
</script>
</head>

<body>
<jsp:include page="header.jsp"/>

     <div class="container">
  <div class="center">
<h2>Login Page</h2>

<form name="myForm" onsubmit="return checkValidEmail()" action="login"  method="post">
    <label><b>Email</b></label><br />
    <input style="height:40px;width:200px" type="text" placeholder="Enter Email" name="email" id="email" required><br /><br />
    <div id ="emailerr"></div>

    <label><b>Password</b></label><br />
    <input style="height:40px;width:200px" type="password" placeholder="Enter Password" name="pwd" required><br /><br />

    <input style="height:40px;width:200px" type="submit" Value="Login"><br><br>
    <div id ="err"></div>

</form>

<a href="homePage.jsp"><button style="height:40px;width:200px" >Go to Home Page</button></a>

      </div>
</div>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<jsp:include page="footer.jsp"/>
</body>
</html>