<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkout</title>
<!-- 静态引用header -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!--//header-->

<!-- ajax实现用户修改购物车项的数量 -->
<script>
function changeNum(productid, num, price, forenum){
	//先把subtotal改了
	$("#a" + productid).html("$" + price * num + ".0");
	
	$.ajax({
  		type : 'get',
  		url : '${pageContext.request.contextPath}/changenum',
  		data : 'productid='+productid+'&num='+num,
  		//回调函数
  		success : function(){
  		}
  	});
	
	//还要改total
	//好久不用js了，写起来真难受
	var total = $("#simpleCart_total").html().toString();
	var sum = total.substring(1, total.length);
	var realsum = "";
	if(sum.length > 6){
		var sub = sum.split(",");
		for(var i = 0 ; i < sub.length ; i ++){
			realsum += sub[i];
		}
	} else{
		realsum = sum;
	}
	
	var gg = parseFloat(realsum);
	gg -= price * forenum;
	gg += price * num;
	sum = gg.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');  
	total = "$" + sum + ".00";
	$("#simpleCart_total").html(total);
}

//这次是批量删除选中项，需要用ajax传递一个数组
function deleteItem(){
	//只传递id数组就够了
	var chk = [];
	//记得改总价
	var total = $("#simpleCart_total").html().toString();
	
	var sum = total.substring(1, total.length);
	
	$('input[name="test"]:checked').each(function(){   //这一行获取选中的复选框
		chk.push($(this).val());
		//把静态页上的对应项删除
		$(this).parent().parent().remove();
		
		var num = parseInt($(this).parent().parent().children(".check").children().val());
		var pric = $(this).parent().parent().children(".checkk").html().toString();
		var price = parseInt(pric.substring(1, pric.length));
		
		
		var realsum = "";
		if(sum.length > 6){
			var sub = sum.split(",");
			for(var i = 0 ; i < sub.length ; i ++){
				realsum += sub[i];
			}
		} else{
			realsum = sum;
		}
		var gg = parseFloat(realsum);
		gg -= price * num;
		sum = gg.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');  
	});
	
	total = "$" + sum + ".00";
	$("#simpleCart_total").html(total);
	
	//再把对应的ID数组传到服务器
	$.ajax({
  		type : 'post',
  		cache : false,
  		traditional : 'true',
  		url : '${pageContext.request.contextPath}/deletecartitem',
  		data : {'chk':chk},
  		dataType : 'json',
  		//回调函数
  		success : function(){
  		}
  	});
}

//用户进入购物车页面时如果还没填写收货地址，就先让用户填上
$(document).ready(function(){
	if(${user.address == null}){
		var address=prompt("Please enter your address","");
		$.ajax({
	  		type : 'post',
	  		url : '${pageContext.request.contextPath}/addaddress',
	  		data : 'address=' + address,
	  		//回调函数
	  		success : function(){
	  		}
	  	});
	}
});
</script>

<div class="container">
	<div class="check-out">
		<h1>Checkout</h1>
		<!-- 循环打印购物车列表 -->
		<form action="checkout" method="post">
    	    <table >
		  <tr>
			<th>Item</th>
			<th>Qty</th>		
			<th>Prices</th>
			<th>Delery Detials</th>
			<th>Subtotal</th>
			<th>Selected</th>
		  </tr>
		  <c:forEach items="${cartlist }" var="item">
		  	<tr>
			<td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/${item.pro.photo }" class="img-responsive" alt=""/></a>
			<div class="sed">
				<h5>${item.pro.name}</h5>
				<p>(${item.pro.info}) </p>
			
			</div>
			<div class="clearfix"> </div></td>
			<td class="check"><input type="text" value="${item.num}" onfocus="this.value='';" onblur="if(this.value == ''){this.value = ${item.num}}else{changeNum(${item.pro.id}, this.value, ${item.pro.price}, ${item.num})}"></td>		
			<td class="checkk">$${item.pro.price}</td>
			<td>FREE SHIPPING</td>
			<td id="a${item.pro.id}">$${item.pro.price * item.num}</td>
			<!-- 批量删除 -->
			<td>
				<input type="checkbox" name="test" value="${item.pro.id}" style="width:17px; height:17px; display:block; margin-left:20px;"/>
			</td>
		  </tr>
		  </c:forEach>
		<!--<tr>
			<td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ce.jpg" class="img-responsive" alt=""></a>
			<div class="sed">
				<h5>Sed ut perspiciatis unde</h5>
				<p>(At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium) </p>
			
			</div>
			<div class="clearfix"> </div></td>
			<td class="check"><input type="text" value="1" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}"></td>		
			<td>$100.00</td>
			<td>FREE SHIPPING</td>
			<td>$100.00</td>
			<td>
				<input type="checkbox" name="checkbox" value="" style="width:17px; height:17px; display:block; margin-left:20px;"/>
			</td>
		  </tr>
		  <tr>
		  <td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ce1.jpg" class="img-responsive" alt=""></a>
			<div class="sed">
				<h5>Sed ut perspiciatis unde</h5>
				<p>(At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium ) </p>
			</div>
			<div class="clearfix"> </div></td>
			<td class="check"><input type="text" value="1" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}"></td>		
			<td>$200.00</td>
			<td>FREE SHIPPING</td>
			<td>$200.00</td>
		  </tr>
		  <tr>
		  <td class="ring-in"><a href="single.jsp" class="at-in"><img src="images/ce2.jpg" class="img-responsive" alt=""></a>
			<div class="sed">
				<h5>Sed ut perspiciatis unde</h5>
				<p>(At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium) </p>
			</div>
			<div class="clearfix"> </div></td>
			<td class="check"><input type="text" value="1" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}"></td>		
			<td>$150.00</td>
			<td>FREE SHIPPING</td>
			<td>$150.00</td>
		  </tr> -->
	</table>
	<a href="generateorder" class=" to-buy">PROCEED TO BUY</a>
	<a href="javascript:void(0)" class=" to-buy" style="position:relative;left:850px;top:-15px" onclick="deleteItem()">DELETE</a>
	</form>
	<div class="clearfix"> </div>
    </div>
</div>
<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!--//footer-->
</body>
</html>