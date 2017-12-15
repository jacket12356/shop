<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 静态引用header -->
<%@ include file="jspf/header.jspf" %>

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">订单详情示例</h2>
      </div>
      <table class="table">
      <tr>
      	<th>缩略图</th>
      	<th>商品名称</th>
      	<th>货号</th>
      	<th>单价</th>
      	<th>数量</th>
      	<th>总价</th>
      	<th>类别</th>
      </tr>
      <c:forEach items="${orderitems}" var="item">
      	<tr>
        <td class="center"><img src="../images/${item.pro.photo }" width="50" height="50"/></td>
        <td>${item.pro.name }</td>
        <td class="center">${item.pro.id }</td>
        <td class="center"><strong class="rmb_icon">${item.pro.price }</strong></td>
        <td class="center"><strong></strong>${item.num }</td>
        <td class="center"><strong class="rmb_icon">${item.pro.price * item.num }</strong></td>
        <td class="center">${item.pro.typeId }</td>
       </tr>
      </c:forEach>
       <!-- <tr>
        <td class="center"><img src="upload/goods01.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center"><strong>1</strong></td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">包</td>
       </tr>
       <tr>
        <td class="center"><img src="upload/goods02.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center"><strong>2</strong></td>
        <td class="center"><strong class="rmb_icon">118.00</strong></td>
        <td class="center">包</td>
       </tr>
       <tr>
        <td class="center"><img src="upload/goods03.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center"><strong>1</strong></td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">包</td>
       </tr> -->
      </table>
 </div>
</section>
</body>
</html>
