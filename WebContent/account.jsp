<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Account</title>
<!-- 静态引用header -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!--//header-->
<div class="account">
	<div class="container">
		<h1>Account</h1>
		<div class="account_grid">
			   <div class="col-md-6 login-right">
				<form action="login" method="post">

					<span>Email Address</span>
					<input type="text" name="mail"/> 
				
					<span>Password</span>
					<input type="password" name="password"/> 
					<div class="word-in">
				 		 <input type="submit" value="Login"/>
				  	</div> 
			    </form>
			   </div>	
			    <div class="col-md-6 login-left">
			  	 <h4>NEW CUSTOMERS</h4>
				 <p>By creating an account with our store, you will be able to move through the checkout process faster, store multiple shipping addresses, view and track your orders in your account and more.</p>
				 <a class="acount-btn" href="register.jsp">Create an Account</a>
			   </div>
			   <div class="clearfix"> </div>
			   <span class="label label-warning" id="warning">${loginError}</span>
			 </div>
	</div>
</div>

<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!--//footer-->
</body>
</html>