

 



function checkValidName (name)
{
	//name will be a string passed to validate
	if(name)//if name is not NULL/empty
	{
		var regex=/^[A-Za-z\s]{2,30}$/;
		if(regex.test(name))
		{
			//NAME IS VALID
			return true;
			
		}
		else
		{
			//name is invalid..it should have min 2 chars, max 30 chars, only a-z and A-Z allowed.
			//hence print error msg
			return false;
		}
	}
	else
	{
		//name is empty/null
		return false;
	}
}

function checkValidEmail (email)
{
	//email will be a string passed to validate
	if(email)//ifemail is not NULL/empty
	{
		var regex=/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
		if(regex.test(email))
		{
			//EMAIL IS VALID
			return true;
			
		}
		else
		{
			//email is invalid..it can have alphabets/nums/@/.
			//hence print error msg
			return false;
		}
	}
	else
	{
		//email is empty/null
		return false;
	}
}

function checkValidType(type)
{
	
	if(type)
	{
		var regex=/\b(?:Tester|Developer|ProjectManager)\b/i;
		if(regex.test(type))
		{
			//ROLE IS VALID
			return true;
			
		}
		else
		{
			//role is invalid..only Tester/projectManager/Developer allowed
			//hence print error msg
			return false;
		}
	}
	else
	{
		//role  is empty/null
		return false;
	}
}


function onChange(event) {



  var obj={};
  obj.a=[];
  var str;
  
  var err="";
  var file = event.target.files[0];
  var path=file.value;
  var regex=/(\.JSON|\.json)$/i;
	if(!regex.exec(path))
	{
		var reader = new FileReader();
  		reader.onload = function(e) {
    	var str=e.target.result;
    	const data=JSON.parse(str);
    	str="";
    	
    	for(let i=0;i<Object.keys(data).length;i++)
    	{
			//here accessing contents of JSON one by one.
			var name=data[i].name;
			var email=data[i].email;
			var type=data[i].type;
			
			if(checkValidName(name)&&checkValidEmail(email)&&checkValidType(type))
			{
				/////////////////////////////////////////////////////////////////////////////////////////////
				//send to DATABASE
				
				str=str+"\""+name+"\""+","+"\""+email+"\""+","+"\""+type+"\""+",";
				
			
				
				
			}
			else
			{
				
				var wrongEntry="name:"+data[i].name+"<br>"+"type:"+data[i].type+"<br>"+"email:"+data[i].email+"<br><br>";
				err=err+wrongEntry;
			
			}
		
		}
		
		
  		param="data="+str;
  		sendToServlet(param);//send correct data to servlet
  		
		if (err)
		{
			err="These entries will not be imported beacause they are incorrect!!!"+"<br><br>"+err;
			document.getElementById("errorMsg").innerHTML=err;
		}
    	
  };

  reader.readAsText(file);
  
	}
	
	
  
}

function sendToServlet(param)
{
	var xhr=new XMLHttpRequest();//http request

	xhr.open('POST', 'http://localhost:8080/Bug_Tracking_System/ImportfileServlet', true);


	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	xhr.onload = function() {//Call a function when the state changes.
	    if(xhr.status == 200) {
	        alert("Correct Users Are Added!");
	    }
	}
	xhr.send(param);
}
