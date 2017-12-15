package com.shop.user.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Cart;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.user.dao.UserDaoImpl;
import com.shop.user.service.UserServiceImpl;  
  
  
@Controller  
public class UserController {  
	
	@Resource
	private UserServiceImpl userServiceImpl;
	
	//改密码
	@RequestMapping("changepassword")
	public void changePassword(@RequestParam("password") String password, HttpSession session) {
		User u = (User) session.getAttribute("user");
		userServiceImpl.changePassword(password, u.getMail());
		//下面的代码能成功运行就表示一对多配好了
//		Set<Order> set = u.getOrderSet();
//		for(Order o : set) {
//			System.out.println(o.getInfo());
//		}
	}
	
	//改个人信息
	@RequestMapping("addinfo")
	public void addInfo(@RequestParam("info") String info, HttpSession session) {
		User u = (User) session.getAttribute("user");
		userServiceImpl.addInfo(info, u.getMail());
	}
	
	
	//登录功能
	//登录时我们还要在session中加入一个购物车，对应登录的用户
	//cart user 一对一
    @RequestMapping("/login")  
    public String login(@RequestParam("mail") String mail,
			@RequestParam("password") String password, HttpServletRequest request, HttpSession session){  
    	//一对一真好用
    	boolean b = userServiceImpl.login(mail, password, request, session);
    	
        if(!b)	return "account";   
        return "index";
    }  
    
    //邮箱验证功能
    @RequestMapping("/mailValidating")
    public @ResponseBody String mailValidating(@RequestParam(value="mail",required=true) String mail) {
    	boolean b = userServiceImpl.isMailExisted(mail);
    	String registError = null;
    	if(b) {
			registError = "mail unavailable";
    	}else {
			registError = "mail available";
    	}
    	return registError;
    }
    
    //注册 
    //整个注册功能包含了：
    //1、接收用户所填的一般信息并插入到数据库表
    //2、接收上传头像并存到一定路径下
    //3、发送账户激活邮件
    //4、为该用户创建一个属于他（她）自己的购物车
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    public String regist(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
    	//为用户创建一个购物车
    	Cart cart = userServiceImpl.createCart(request);
    	
    	//将基本的用户信息存入数据库表中
    	userServiceImpl.addUser(request, file.getOriginalFilename(), cart);
    	
    	//将头像图片接收到icons路径下
    	boolean isUploadSucceed = userServiceImpl.iconUpload(file, request);
    	
    	//激活邮件发到用户填写的邮箱下
    	userServiceImpl.sendMail(request);
    	
    	
    	
    	return "redirect:index.jsp";
    }
    
    //激活一个账户
    @RequestMapping("/activate")
    public String activate(HttpServletRequest request) {
    	String[] m = request.getParameter("mail").split("-");
    	for(String s : m) {
    		System.out.println(s);
    	}
    	String ma = m[0] + "@" + m[1];
    	userServiceImpl.activateAccount(ma);
    	return "redirect:index.jsp";
    }
    
    
    //ajax_测试1
    @RequestMapping("/jsonsource")
    public @ResponseBody User jsonSource(@RequestBody User user){
    		user.setName("yang");
           return user;
    }
    //ajax_测试2
    //@ResponseBody
    @RequestMapping("/kvsource")
    public @ResponseBody String KVSource(@RequestParam(value="name",required=true) String name, @RequestParam(value="password",required=true) String password){
    	User u = new User();
    	u.setName(name);
    	u.setPassword(password);
        return name;
    }
    //ajax_测试3
    @RequestMapping("/jsonsource_1")
    public @ResponseBody User jsonSource_1(@RequestBody User user){
    		user.setName(user.getName() + "_changed");
    		user.setPassword(user.getPassword() + "_changed");
            return user;
    }
}  