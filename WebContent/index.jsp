<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>

<!-- 静态引用header -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<script>
//ajax作添加购物车用,onclick触发
function addProduct(productid){
	$.ajax({
   		type : 'get',
   		url : '${pageContext.request.contextPath}/addproduct',
   		data : 'productid='+productid,
   		//回调函数
   		success : function(){
   		}
   	});
}
</script>

<!--banner-->
<div class="banner">
	<div class="col-sm-3 banner-mat">
		<img class="img-responsive"	src="images/ba1.jpg" alt="">
	</div>
	<div class="col-sm-6 matter-banner">
	 	<div class="slider">
	    	<div class="callbacks_container">
	      		<ul class="rslides" id="slider">
	        		<li>
	          			<img src="images/1.jpg" alt="">
	       			 </li>
			 		 <li>
	          			<img src="images/2.jpg" alt="">   
	       			 </li>
					 <li>
	          			<img src="images/1.jpg" alt="">
	        		</li>	
	      		</ul>
	 	 	</div>
		</div>
	</div>
	<div class="col-sm-3 banner-mat">
		<img class="img-responsive" src="images/ba.jpg" alt="">
	</div>
	<div class="clearfix"> </div>
</div>
<!--//banner-->
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
<!--content-->
<div class="content">
	<div class="container">
		<div class="content-top">
			<h1>Recent Products</h1>
			<div class="content-top1">
			<c:forEach items="${newproductlist }" var="p">
			<!-- 这里需要改5处呢 除了3处商品基本信息，还有商品详情链接、商品加入购物车链接 -->
				<div class="col-md-3 col-md2" style="margin-bottom:45px;">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/${p.photo}" alt="" />
						</a>
						<h3><a href="single.jsp">${p.name }</a></h3>
						<div class="price">
								<h5 class="item_price">$${p.price }</h5>
								<a href="javascript:void(0)" class="item_add" onclick="addProduct(${p.id})">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
					</div>
				</div>	
			</c:forEach>
				
			<!-- 	<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi.png" alt="" />
						</a>
						<h3><a href="single.jsp">Tops</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi2.png" alt="" />
						</a>
						<h3><a href="single.jsp">T-Shirt</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi4.png" alt="" />
						</a>
						<h3><a href="single.jsp">Shirt</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi1.png" alt="" />
						</a>
						<h3><a href="single.jsp">Tops</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="clearfix"> </div>
			</div>	
			<div class="content-top1">
				<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi3.png" alt="" />
						</a>
						<h3><a href="single.jsp">Shirt</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi5.png" alt="" />
						</a>
						<h3><a href="single.jsp">T-Shirt</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi6.png" alt="" />
						</a>
						<h3><a href="single.jsp">Jeans</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-3 col-md2">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi7.png" alt="" />
						</a>
						<h3><a href="single.jsp">Tops</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
				
				
				
				
				 -->
			<div class="clearfix"> </div>
			</div>	
		</div>
	</div>
</div>
<!--//content-->
<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>