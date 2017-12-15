<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 静态引用header -->
<%@ include file="jspf/header.jspf" %>

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">商品列表示例</h2>
       <a href="addproduct_detail" class="fr top_rt_btn add_icon">添加商品</a>
      </div>
      <section class="mtb">
      <form action="search" method="get">
       <input type="text" name="keyword" class="textbox textbox_225" placeholder="输入产品关键词..."/>
       <input type="submit" value="查询" class="group_btn"/>
      </form>
      </section>
      <table class="table">
       <tr>
        <th>缩略图</th>
        <th>产品名称</th>
        <th>货号</th>
        <th>单价</th>
        <th>类别</th>
        <th>精品</th>
        <th>新品</th>
        <th>热销</th>
        <th>操作</th>
       </tr>
       <c:forEach items="${productlist}" var="p">
       	<tr>
        <td class="center"><img src="../images/${p.photo}" width="50" height="50"/></td>
        <td>${p.name}</td>
        <td class="center">${p.id}</td>
        <td class="center"><strong class="rmb_icon">${p.price}</strong></td>
        <td class="center">${p.typeId}</td>
        
        <td class="center"><a title="是" class="link_icon">&#89;</a></td>
        <c:if test="${p.isNew == 1}">
        	<td class="center"><a class="link_icon">&#88;</a></td>
        </c:if>
        <c:if test="${p.isNew == 2}">
        	<td class="center"><a class="link_icon">&#89;</a></td>
        </c:if>
        <c:if test="${p.isHot == 1}">
        	<td class="center"><a class="link_icon">&#88;</a></td>
        </c:if>
        <c:if test="${p.isHot == 2}">
        	<td class="center"><a class="link_icon">&#89;</a></td>
        </c:if>
        <td class="center">
         <a href="changeproduct_detail?id=${p.id}" title="编辑" class="link_icon">&#101;</a>
         <a href="deleteproduct?id=${p.id }" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       </c:forEach>
      <!-- <tr>
        <td class="center"><img src="upload/goods01.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">包</td>
        <td class="center"><a title="是" class="link_icon">&#89;</a></td>
        <td class="center"><a title="否" class="link_icon">&#88;</a></td>
        <td class="center"><a title="是" class="link_icon">&#89;</td>
        <td class="center">
         <a href="http://www.mycodes.net" title="预览" class="link_icon" target="_blank">&#118;</a>
         <a href="product_detail.jsp" title="编辑" class="link_icon">&#101;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       <tr>
        <td class="center"><img src="upload/goods02.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        <td class="center">A15902</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">包</td>
        <td class="center"><a title="是" class="link_icon">&#89;</a></td>
        <td class="center"><a title="否" class="link_icon">&#88;</a></td>
        <td class="center"><a title="是" class="link_icon">&#89;</a></td>
        <td class="center">
         <a href="http://www.mycodes.net" title="预览" class="link_icon" target="_blank">&#118;</a>
         <a href="product_detail.jsp" title="编辑" class="link_icon">&#101;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr> --> 
      </table>
      <aside class="paging">
       <a>第一页</a>
       <a>1</a>
       <a>2</a>
       <a>3</a>
       <a>…</a>
       <a>1004</a>
       <a>最后一页</a>
      </aside>
 </div>
</section>
</body>
</html>
