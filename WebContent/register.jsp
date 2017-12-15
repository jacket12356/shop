<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
<!-- 静态引用header -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- AJAX 作邮箱验证用的-->
<script>
function sendForm(){
	var mail = $("#mail").val();
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 if(!myreg.test(mail))
	 {
		 $("#mail").focus();
		 $("#warning").html("warning: Incorrect mail format!");
	     return false;
	}
	
	//一般的表单验证
	if(mail != "") {  
		$.ajax({
    		type : 'post',
    		url : '${pageContext.request.contextPath}/mailValidating',
    		data : 'mail='+mail,
    		//回调函数
    		success : function(error){
    			$("#warning").html("warning: "+error);
    		}
    	});
    }
}
function nameCheck(){
    var name = $("#name").val();  
    
    if(name == "") {  
        $("#name").focus();  
        $("#warning").html("warning: A empty name!");
        return false;
    }  
    $("#warning").html("");
}

function passwordCheck(){
	var password = $("#password").val();  
	var repassword = $("#repassword").val();
	
	if(password == "") {  
        $("#password").focus();  
        $("#warning").html("warning: A empty password!");
        return false;
    }  
      
    if(password.length < 6) {  
        $("#password").focus();  
        $("#warning").html("warning: password lengh less than 6!");
        return false;
    }  
      
    if(repassword != password) {  
        $("#repassword").focus();  
        $("#warning").html("warning: Inconsistent password and repassword!");
        return false;
    }  
    $("#warning").html("");
}
 </script>
<div class="container">
	<div class="register">
		<h1>Register</h1>
		  	  <form action="regist" method="post" enctype="multipart/form-data">
				 <div class="col-md-6  register-top-grid">
					
					<div class="mation">
						<span>name</span>
						<input type="text" id="name" onblur="nameCheck()" name="name"/>
					 		<!-- 邮箱验证 -->
						 <span>Email Address</span> 
						 <input type="text" onblur="sendForm()" id="mail" name="mail"/> 
						 
						 <span>Your Icon</span> 
						 <input type="file" name="file"/> 
					</div>
					
					 <div class="clearfix"> </div>
					   <a class="news-letter" href="#">
						 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>Sign Up</label>
					   </a>
					 </div>
				     <div class=" col-md-6 register-bottom-grid">
						   
							<div class="mation">
								<span>Password</span>
								<input type="text" id="password" onblur="passwordCheck()" name="password"/>
								<span>Confirm Password</span>
								<input type="text" id="repassword" onblur="passwordCheck()"/>
							</div>
					 </div>
					 <div class="clearfix"> </div>
				
				<div class="register-but">
				   
					   <input type="submit" value="submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   	<span class="label label-warning" id="warning"></span>
				   </form>
				</div>
		   </div>
</div>

<!--footer-->
<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!--//footer-->
</body>
</html>