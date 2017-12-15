<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 静态引用header -->
<%@ include file="jspf/header.jspf" %>

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">订单列表示例</h2>
       <a class="fr top_rt_btn add_icon">添加商品</a>
      </div>
      <section class="mtb">
       <input type="text" class="textbox textbox_225" placeholder="输入订单编号或收件人姓名/电话..."/>
       <input type="button" value="查询" class="group_btn"/>
      </section>
      <table class="table">
       <tr>
        <th>订单编号</th>
        <th>发件人</th>
        <th>订单金额</th>
        <th>配送方式</th>
        <th>操作</th>
       </tr>
       <c:forEach items="${orderlist }" var="item">
       <tr>
        <td class="center">${item.id }</td>
        <td style="text-align:center;">${item.name }</td>
        <td class="center"><strong class="rmb_icon">${item.total }</strong></td>
        <td class="center">申通快递</td>
        <td class="center">
         <a href="orderdetail?id=${item.id }" title="查看订单" class="link_icon" target="_blank">&#118;</a>
         <a href="deleteorder?id=${item.id }" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       </c:forEach>
       <!-- <tr>
        <td class="center">201602011207</td>
        <td>DeathGhost</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">申通快递</td>
        <td class="center">
         <a href="#" title="查看订单" class="link_icon" target="_blank">&#118;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       <tr>
        <td class="center">201602011207</td>
        <td>DeathGhost</td>
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center">圆通快递</td>
        <td class="center">
         <a href="#" title="查看订单" class="link_icon" target="_blank">&#118;</a>
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
