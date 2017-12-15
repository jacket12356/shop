<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Products</title>
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

<!--content-->
<div class="products">
	<div class="container">
		<h1>Products</h1>
		<div class="col-md-9">
			
			<!-- 也是那5处 -->
			<c:forEach items="${productlist}" var="p">
				<div class="col-md-4 col-md3" style="margin-bottom:45px;">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img style="display:block;width:185px;height:207px;" class="img-responsive" src="images/${p.photo }" alt=""/>
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
				<!-- <div class="col-md-4 col-md3">
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
			<div class="col-md-4 col-md3">
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
			<div class="col-md-4 col-md3">
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
			
			<div class="clearfix"> </div>
			</div>	
			<div class="content-top1">
				<div class="col-md-4 col-md3">
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
			<div class="col-md-4 col-md3">
					<div class="col-md1 simpleCart_shelfItem">
						<a href="single.jsp">
							<img class="img-responsive" src="images/pi3.png" alt="" />
						</a>
						<h3><a href="single.jsp">T-Shirt</a></h3>
						<div class="price">
								<h5 class="item_price">$300</h5>
								<a href="#" class="item_add">Add To Cart</a>
								<div class="clearfix"> </div>
						</div>
						
					</div>
				</div>	
			<div class="col-md-4 col-md3">
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
				</div>	 -->
			
			
			<div class="content-top1">
				<!-- <div class="col-md-4 col-md3">
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
			<div class="col-md-4 col-md3">
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
			<div class="col-md-4 col-md3">
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
				</div>	 -->
			
			<div class="clearfix"> </div>
			</div>	
		</div>
		<div class="col-md-3 product-bottom">
			<!--categories-->
				<div class=" rsidebar span_1_of_left">
						<h3 class="cate">Categories</h3>
						
							 <ul class="menu-drop">
							 
							 <li class="item2"><a href="#">Click and show! </a>
								<ul class="cute">
								<c:forEach items="${typelist}" var="type">
									<li><a href="productlist?typeid=${type.id }">${type.name}</a></li>
								</c:forEach>
								</ul>
							</li>
							
							 
							 
							<!-- <li class="item1"><a href="#">Men </a>
								<ul class="cute">
									<li class="subitem1"><a href="single.jsp">Cute Kittens </a></li>
									<li class="subitem2"><a href="single.jsp">Strange Stuff </a></li>
									<li class="subitem3"><a href="single.jsp">Automatic Fails </a></li>
								</ul>
							</li>
							<li class="item2"><a href="#">Women </a>
								<ul class="cute">
									<li class="subitem1"><a href="single.jsp">Cute Kittens </a></li>
									<li class="subitem2"><a href="single.jsp">Strange Stuff </a></li>
									<li class="subitem3"><a href="single.jsp">Automatic Fails </a></li>
								</ul>
							</li>
							<li class="item3"><a href="#">Kids</a>
								<ul class="cute">
									<li class="subitem1"><a href="single.jsp">Cute Kittens </a></li>
									<li class="subitem2"><a href="single.jsp">Strange Stuff </a></li>
									<li class="subitem3"><a href="single.jsp">Automatic Fails</a></li>
								</ul>
							</li>
							<li class="item4"><a href="#">Accesories</a>
								<ul class="cute">
									<li class="subitem1"><a href="single.jsp">Cute Kittens </a></li>
									<li class="subitem2"><a href="single.jsp">Strange Stuff </a></li>
									<li class="subitem3"><a href="single.jsp">Automatic Fails</a></li>
								</ul>
							</li>
									
							<li class="item4"><a href="#">Shoes</a>
								<ul class="cute">
									<li class="subitem1"><a href="product.jsp">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.jsp">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.jsp">Automatic Fails </a></li>
								</ul>
							</li>-->
						</ul> 
					</div>
				<!--initiate accordion-->
						<script type="text/javascript">
							$(function() {
							    var menu_ul = $('.menu-drop > li > ul'),
							           menu_a  = $('.menu-drop > li > a');
							    menu_ul.hide();
							    menu_a.click(function(e) {
							        e.preventDefault();
							        if(!$(this).hasClass('active')) {
							            menu_a.removeClass('active');
							            menu_ul.filter(':visible').slideUp('normal');
							            $(this).addClass('active').next().stop(true,true).slideDown('normal');
							        } else {
							            $(this).removeClass('active');
							            $(this).next().stop(true,true).slideUp('normal');
							        }
							    });
							
							});
						</script>
<!--//menu-->
<!--seller-->
				<div class="product-bottom">
					<h3 class="cate">Best Sellers</h3>
					<!-- 最热商品展示，这里能改三处 -->
					<c:forEach items="${hotproductlist}" var="p">
						<div class="product-go">
							<div class=" fashion-grid">
								<a href="single?productid=${p.id }"><img class="img-responsive " src="images/${p.photo }" alt=""></a>	
							</div>
							<div class=" fashion-grid1">
								<h6 class="best2"><a href="single?productid=${p.id }" >${p.name }  </a></h6>
								<span class=" price-in1"> $${p.price }</span>
							</div>	
							<div class="clearfix"> </div>
						</div>
					</c:forEach>
					<!-- <div class="product-go">
						<div class=" fashion-grid">
							<a href="single.jsp"><img class="img-responsive " src="images/pr.jpg" alt=""></a>	
						</div>
						<div class=" fashion-grid1">
							<h6 class="best2"><a href="single.jsp" >Lorem ipsum dolor sitamet consectetuer  </a></h6>
							<span class=" price-in1"> $40.00</span>
						</div>	
						<div class="clearfix"> </div>
					</div>
					<div class="product-go">
						<div class=" fashion-grid">
							<a href="single.jsp"><img class="img-responsive " src="images/pr1.jpg" alt=""></a>	
						</div>
						<div class=" fashion-grid1">
							<h6 class="best2"><a href="single.jsp" >Lorem ipsum dolor sitamet consectetuer  </a></h6>
							<span class=" price-in1"> $40.00</span>
						</div>	
						<div class="clearfix"> </div>
					</div>
					<div class="product-go">
						<div class=" fashion-grid">
							<a href="single.jsp"><img class="img-responsive " src="images/pr2.jpg" alt=""></a>	
						</div>
						<div class=" fashion-grid1">
							<h6 class="best2"><a href="single.jsp" >Lorem ipsum dolor sitamet consectetuer  </a></h6>
							<span class=" price-in1"> $40.00</span>
						</div>	
						<div class="clearfix"> </div>
					</div>	
					<div class="product-go">
						<div class=" fashion-grid">
							<a href="single.jsp"><img class="img-responsive " src="images/pr3.jpg" alt=""></a>	
						</div>
						<div class=" fashion-grid1">
							<h6 class="best2"><a href="single.jsp" >Lorem ipsum dolor sitamet consectetuer  </a></h6>
							<span class=" price-in1"> $40.00</span>
						</div>	
						<div class="clearfix"> </div>
					</div>	 -->	
				</div>

<!--//seller-->
<!--tag-->
				<div class="tag">	
						<h3 class="cate">Tags</h3>
					<div class="tags">
						<ul>
							<li><a href="#">design</a></li>
							<li><a href="#">fashion</a></li>
							<li><a href="#">lorem</a></li>
							<li><a href="#">dress</a></li>
							<li><a href="#">fashion</a></li>
							<li><a href="#">dress</a></li>
							<li><a href="#">design</a></li>
							<li><a href="#">dress</a></li>
							<li><a href="#">design</a></li>
							<li><a href="#">fashion</a></li>
							<li><a href="#">lorem</a></li>
							<li><a href="#">dress</a></li>
						<div class="clearfix"> </div>
						</ul>
				</div>					
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
<!--//content-->
<!--footer-->
<!-- 静态引用footer -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!--//footer-->
</body>
</html>