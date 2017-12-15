<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>
<script>
function requestedByJson() {

    $.ajax({

        type : 'post',

        url : '${pageContext.request.contextPath}/jsonsource',

        //设置contentType类型为json

        contentType : 'application/json;charset=utf-8',

        //json数据

        data : '{"name":"reader001","password":"psw001"}',

        //请求成功后的回调函数

        success : function(data) {

                 alert(data.name);

        }

    });

}
</script>

<script>
function resquestedByKV() {

    $.ajax({

         type : 'post',

         url : '${pageContext.request.contextPath}/kvsource',

         data : 'name=kvuser&password=kvpsw',

         success : function(data) {

                  alert(data);

         }

    });

}
function sendForm(){
	var u1 = $("#u1").val();
	var p1 = $("#p1").val();

	$.ajax({
		type : 'post',
		url : '${pageContext.request.contextPath}/jsonsource_1',
		contentType : 'application/json;charset=utf-8',
		data : '{' + '"name":'+'"'+u1+'"'+ ',' +'"password":' +'"'+p1+'"'+'}',
		success : function(data){
			alert(data.name+"----"+data.password);
			$("#u2").val(data.name);
			$("#p2").val(data.password);
		}
	});
}
</script>

</head>
<body>
	<form>
		<input type="button" value="json" onclick="requestedByJson()"/><br/>
		<input type="button" value="keyvalue" onclick="resquestedByKV()"/>
	</form>
	<br/><br/><br/><br/><br/><br/><br/>
	
	<form>
		用户名：<input type="text" id="u1" name="name"/><br/>
		密码：<input type="password" id="p1" name="password"/><br/>
		<input type="button" value="submit" onclick="sendForm()">
	</form>
	<br/><br/>
	<form>
		用户名：<input type="text" id="u2" name="name"/><br/>
		密码：<input type="password" id="p2" name="password"/>
	</form>
</body>
</html>