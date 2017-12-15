package com.shop.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.CartItem;
import com.shop.order.dao.OrderDaoImpl;
import com.shop.order.service.OrderServiceImpl;

@Controller
public class OrderController {
	@Resource
	private OrderServiceImpl orderServiceImpl;
	
	//用户进入个人详情页面
	//先把订单集合解析出来存session里
	//这个订单集合的元素其实是CartItem的集合
	//根据订单集合生成一个分页对象，利用分页和AJAX动态显示订单信息
	@RequestMapping("personal")
	public String personal(HttpSession session, HttpServletRequest request) {
		orderServiceImpl.personal(session, request);
		return "personal";
	}
	
	@RequestMapping("changeorder")
	public @ResponseBody String changeOrder(HttpSession session, HttpServletRequest request, @RequestParam("currentpage") int currentPage) {
		return orderServiceImpl.changeOrder(session, request, currentPage);
	}
}
