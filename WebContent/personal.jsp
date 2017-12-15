<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js"></script>
<style>
#box1 {
	width:500px; height:500px;
	margin-top:20px;
	margin-left:121px;
	float:left;
}
#box2 {
	padding:20px;
	outline:1px solid violet;
	width:600px; height:420px;
	margin:20px;
	margin-bottom:5px;
	float:left;
	color: grey;
}
#box3 {
	width:600px; height:68px;
	margin-left:20px;
	margin-top:5px;
	margin-bottom:100px;
	float:left;
}
span {
	color: #b2b2b2;
	font-size:0.9em;
	padding-bottom: 0.4em;
	display: block;
}
input[type="password"] {
	border: 1px solid #bbb;
	outline-color:#52d0c4;
	width: 96%;
	font-size:0.9em;
	padding:10px;
	margin: 0.5em 0;
}
textarea {
	border: 1px solid #bbb;
	outline-color:#52d0c4;
	width: 96%;
	font-size:0.9em;
	padding:10px;
	margin: 0.5em 0;
}
input[type="button"] {
	background:#52d0c4;
	color: #FFF;
	font-size: 0.9em;
	padding: 0.4em 1em;
	transition: 0.5s all;
	-webkit-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-o-transition: 0.5s all;
	display: inline-block;
	text-transform: uppercase;
	border:none;
	outline:none;
}
input[type="button"]:hover {
	background:#000;
}

div.page {
float:left; outline:1px solid aliceblue;  text-align:center; display:inline-box;
margin-left:10px; margin-right:10px;width:55px; height:20px; background-color:aliceblue;
}
div.page a {text-decoration:none; color:black;}

a.pagee {
	display:inline-block; 
	width:40px; height:40px; 
	margin:10px;text-decoration:none; 
	padding-top:10px;
	background-color:#52d0c4;
	text-align:center;
	color:white;
}
a.pagee:hover {
	background:#000;
	color:white;
	text-decoration:none; 
}
#box2 h4{
	line-height:50px;
}
</style>
<script>
	function changePassword(){
		var password = $("#password").val();
		$.ajax({
	  		type : 'get',
	  		url : '${pageContext.request.contextPath}/changepassword',
	  		data : 'password=' + password,
	  		//回调函数
	  		success : function(){
	  		}
	  	});
		$("#password").val(null);
		alert("Action successful!");
	}
	function addInfo(){
		var info = $("#info").val();
		$.ajax({
	  		type : 'get',
	  		url : '${pageContext.request.contextPath}/addinfo',
	  		data : 'info=' + info,
	  		//回调函数
	  		success : function(){
	  		}
	  	});
		$("#info").val(null);
		alert("Action successful!");
	}
	function changeOrder(currentpage){
		$.ajax({
	  		type : 'get',
	  		url : '${pageContext.request.contextPath}/changeorder',
	  		data : 'currentpage=' + currentpage,
	  		//回调函数
	  		success : function(text){
	  			$("#box2").html(text);
	  		}
	  	});
		
	}
</script>
<title>Personal</title>
<!-- 静态引用header -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div id="box1">
<!-- 应该是写到User里 -->
<form>
	<span>Re Password</span>
	<input type="password" id="password"/><br/><br/>
	<input type="button" value="submit" onclick="changePassword()"/>  <br/><br/><br/><br/>
	<span>Add Info</span>
	<textarea rows="8" cols="20" id="info"></textarea><br/><br/>
	<input type="button" value="submit" onclick="addInfo()"/>
</form>
</div>

<div id="box2">
<h4>My Order</h4>
<c:forEach items="${curorder}" var="item">
	<p>${item.pro.name}: $${item.pro.price} * ${item.num} = $${item.pro.price * item.num}</p>
</c:forEach>
<h4>Total : $${total}.0</h4>
</div>

<div id="box3">
<center>
	<c:forEach items="${page.pages }" var="c">
		<a href="javascript:void(0)" onclick="changeOrder(${c })" class="pagee">${c }</a>
	</c:forEach>
</center>
</div>
<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>