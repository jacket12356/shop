package com.shop.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.manager.service.ManagerServiceImpl;

@Controller  
public class ManagerController {
	@Resource
	private ManagerServiceImpl managerServiceImpl;
	
//	@RequestMapping("/admin/lala")
//	public String regist() {
//		managerServiceImpl.addUser("rootuser", "123456");
//		return "admin/login";
//	}
	
	//登录
	@RequestMapping("/admin/login")
	public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
		boolean b = managerServiceImpl.login(account, password);
		if(b) return "admin/index";
		return "admin/login";
	}
	
	//用户点击商品列表
	@RequestMapping("/admin/productlist")
	public String productList(HttpServletRequest request) {
		managerServiceImpl.productList(request);
		return "admin/product_list";
	}
	
	//商品查询
	@RequestMapping("/admin/search")
	public String search(@RequestParam("keyword") String keyWord, HttpServletRequest request) {
		managerServiceImpl.search(keyWord, request);
		return "admin/product_list";
	}
	
	//转到添加商品页面
	@RequestMapping("/admin/addproduct_detail")
	public String addproductDetail(HttpServletRequest request) {
		managerServiceImpl.addproductDetail(request);
		return "admin/addproduct_detail";
	}
	
	//添加商品
	@RequestMapping("/admin/addproduct")
	public String addProduct(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		//先把基本信息存到数据库
		managerServiceImpl.addProduct(file, request);
		//在将上传的图片放起来
		managerServiceImpl.iconUpload(file, request);
		
		managerServiceImpl.productList(request);
		return "admin/product_list";
	}
	
	//跳转编辑商品
	@RequestMapping("/admin/changeproduct_detail")
	public String changeproductDetail(HttpServletRequest request) {
		managerServiceImpl.changeproductDetail(request);
		return "admin/changeproduct_detail";
	}
	
	//编辑商品
	@RequestMapping("/admin/changeproduct")
	public String changeProduct(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		//先把基本信息更新到数据库
		managerServiceImpl.changeProduct(file, request);
		//在将上传的图片放起来
		managerServiceImpl.iconUpload(file, request);
		
		managerServiceImpl.productList(request);
		return "admin/product_list";
	}
	
	//删除商品
	@RequestMapping("/admin/deleteproduct")
	public String deleteProduct(HttpServletRequest request) {
		managerServiceImpl.deleteProduct(request);
		
		managerServiceImpl.productList(request);
		return "admin/product_list";
	}
	
	//跳转到订单列表
	@RequestMapping("/admin/orderlist")
	public String orderList(HttpServletRequest request) {
		managerServiceImpl.orderList(request);
		return "admin/order_list";
	}
	
	//跳转到订单详情
	@RequestMapping("/admin/orderdetail")
	public String orderDetail(@RequestParam("id") String oid, HttpServletRequest request) {
		int id = Integer.parseInt(oid);
		managerServiceImpl.orderDetail(id, request);
		return "admin/order_detail";
	}
	
	//删除订单
	@RequestMapping("/admin/deleteorder")
	public String deleteOrder(@RequestParam("id") String oid, HttpServletRequest request) {
		int id = Integer.parseInt(oid);
		managerServiceImpl.deleteOrder(id);
		
		managerServiceImpl.orderList(request);
		return "admin/order_list";
	}
	
	//用户列表跳转
	@RequestMapping("/admin/userlist")
	public String userList(HttpServletRequest request) {
		managerServiceImpl.userList(request);
		return "admin/user_list";
	}
	
	//增加新用户
	@RequestMapping("/admin/adduser")
	public String addUser(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		managerServiceImpl.addUser(file, request);
		return "admin/user_list";
	}
	
	//编辑用户跳转
	@RequestMapping("/admin/userdetail")
	public String userDetail(@RequestParam("id") String oid, HttpServletRequest request) {
		managerServiceImpl.userDetail(oid, request);
		return "admin/user_detail";
	}
	
	//编辑用户
	@RequestMapping("/admin/changeuser")
	public String changeUser(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		managerServiceImpl.changeUser(file, request);
		
		managerServiceImpl.userList(request);
		return "admin/user_list";
	}
	
	//删除用户
	@RequestMapping("/admin/deleteuser")
	public String deleteUser(@RequestParam("id") String oid, HttpServletRequest request) {
		managerServiceImpl.deleteUser(oid);
		
		managerServiceImpl.userList(request);
		return "admin/user_list";
	}
}
