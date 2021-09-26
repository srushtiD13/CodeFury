<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
        function validatePassword() {
            let x = document.forms["myForm"]["pwd"].value;
            var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            if (regex.test(x) == false) {
                alert(" Password must contain minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:")
                return false;
            }
        }
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
<form name="myForm" onsubmit="return checkValidEmail()" action="register" method="post" onsubmit="return validatePassword()">




        <label><b>Email</b></label><br />
        <input style="height:30px;width:200px" type="text" placeholder="Enter Email" name="email" id="email" required><br /><br />

        <label><b>Role</b></label><br />
        <select style="height:40px;width:200px" name="role" id="Role" required>
            <option value="Tester">Tester</option>
            <option value="Developer">Developer</option>
            <option value="ProjectManager">Project Manager</option>

        </select><br /><br />

        <label><b>Password</b></label><br />
        <input style="height:30px;width:200px" type="password" placeholder="Enter Password" name="password" id="password" required><br /><br />


        <input  style="height:40px;width:200px" type="submit" Value="Register"><br /><br />

        <div id="err"></div><br />
        
    </form>
    <a href="homePage.jsp"><button style="height:40px;width:200px" >Go to Home Page</button></a>
          </div>
</div>
<br/><br/>
<br/><br/>
<br/><br/>
<jsp:include page="footer.jsp"/>
</body>
</html>