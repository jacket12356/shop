<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 静态引用header -->
<%@ include file="jspf/header.jspf" %>

<section class="rt_wrap content mCustomScrollbar">
<form action="changeuser" method="post" enctype="multipart/form-data">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">会员详情</h2>
       <a href="adjust_funding.jsp" class="fr top_rt_btn money_icon">资金管理</a>
      </div>
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">上传头像：</span>
        <label class="uploadImg">
         <input type="file" name="file"/>
         <span>上传头像</span>
        </label>
       </li>
       <li>
        <span class="item_name" name="name" style="width:120px;">会员名称：</span>
        <input type="text" class="textbox textbox_225" value="${u.name }" placeholder="会员账号..."/>
       </li>
        <li>
        <span class="item_name" name="mail" style="width:120px;">电子邮箱：</span>
        <input type="email" class="textbox textbox_225" value="${u.mail }" placeholder="电子邮件地址..."/>
       </li>
       <li>
        <span class="item_name" name="password" style="width:120px;">登陆密码：</span>
        <input type="password" class="textbox textbox_225" value="${u.password }" placeholder="会员密码..."/>
       </li>
      
       <li>
        <span class="item_name" name="address" style="width:120px;">收货地址：</span>
        <input type="text" class="textbox textbox_225" value="${u.address }" placeholder="收货地址..."/>
       </li>
       <li>
        <span class="item_name" name="info" style="width:120px;">个人信息：<br/><br/><br/><br/><br/><br/><br/><br/></span>
        <textarea class="textbox textbox_225" value="${u.info }" placeholder="个人信息..." style="height:200px;"></textarea>
       </li>
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn" value="更新/保存"/>
       </li>
      </ul>
 </div>
 </form>
</section>
</body>
</html>
