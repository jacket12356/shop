package com.shop.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.cart.service.CartServiceImpl;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.User;

@Controller
public class CartController {
	@Resource
	private CartServiceImpl cartServiceImpl;
	
	//添加收货地址
	//要存到数据库里去，还要存到当前session的User对象下
	@RequestMapping("addaddress")
	public void addAddress(HttpServletRequest request, HttpSession session) {
		cartServiceImpl.addAddress(request, session);
	}
	
	//生成订单
	@RequestMapping("generateorder")
	public String generateOrder(HttpSession session) {
		cartServiceImpl.generateOrder(session);
		
		return "redirect:index.jsp";
	}
	
	//用户使用批量删除
	@RequestMapping("deletecartitem")
	@ResponseBody
	public void deleteCartItem(@RequestParam("chk") String[] chk, HttpSession session) {//竟然一下成功了
		cartServiceImpl.deleteItem(chk, session);
	}
	
	//用户在结算页面修改了一个商品的购买数量
	@RequestMapping("changenum")
	public void changeNum(HttpServletRequest request, HttpSession session) {
		cartServiceImpl.changeNum(request, session);
	}
	
	//用户转到购物车结算界面
	//购物车功能具体是：
	//一开始解析info，生成一个CartItem集合
	//之后用户在结算的操作都会是先对CartItem集合进行更改，再将这个集合转成info并更新到数据库里
	//最后在生成订单时将集合转成info并更新到数据库里
	@RequestMapping("/checkout")
	public String checkOut(HttpServletRequest request, HttpSession session) {
		cartServiceImpl.cartCheckOut(request, session);
		
		return "checkout";
	}
	
	//清空购物车，也得用ajax弄
	@RequestMapping("emptycart")
	public void emptyCart(HttpSession session) {
		cartServiceImpl.emptyInfo(session);
	}
	
	//用户点击"add to cart",这一点用ajax弄
	//按现在的情况看，可能用户每修改一下购物情况，我就要把它更新到数据库
	//等到结算和清空的时候不能忘了清空购物车
	@RequestMapping("addproduct")
	public void addProduct(@RequestParam(value="productid", required=true) int productId, HttpSession session) {
		cartServiceImpl.addProduct(productId, session);
	}
}
