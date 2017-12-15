<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery.dialog.js"></script>
<link rel="stylesheet" type="text/css" href="css/dialog.css">
<script type="text/javascript">
$(document).ready(function(){
	$("#alert-default").click(function(){
		$.dialog.alert({content:"这是普通提示信息"});
	});
});
</script>
</head>
<body>
	<input type="button" id="alert-default" value="alert默认" />
</body>
</html>