<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 静态引用header -->
<%@ include file="jspf/header.jspf" %>

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">会员列表</h2>
       <a href="adduser_detail.jsp" class="fr top_rt_btn add_icon">添加新会员</a>
      </div>
      <section class="mtb">
      </section>
      <table class="table">
       <tr>
        <th>会员头像</th>
        <th>会员昵称</th>
        <th>电子邮件</th>
        <th>验证</th>
        <th>收货地址</th>
        <th>个人信息</th>
        <th>操作</th>
       </tr>
       <c:forEach items="${userlist }" var="u">
       	<tr>
        <td class="center"><img src="../icons/${u.iconPath }" width="50" height="50"/></td>
        <td>${u.name }</td>
        <td class="center">${u.mail }</td>
        <c:if test="${u.activated == 2 }">
        <td class="center"><a title="已验证" class="link_icon">&#89;</a></td>
        </c:if>
        <c:if test="${u.activated == 1 }">
        <td class="center"><a title="未验证" class="link_icon">&#88;</a></td>
        </c:if>
        
        <td class="center">${u.address }</td>
        <td class="center">${u.info }</td>
        <td class="center">
         <a href="userdetail?id=${u.mail }" title="编辑" class="link_icon">&#101;</a>
         <a href="deleteuser?id=${u.mail }" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       </c:forEach>
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
