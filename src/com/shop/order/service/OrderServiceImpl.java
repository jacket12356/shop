package com.shop.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.Page;
import com.shop.entity.User;
import com.shop.order.dao.OrderDaoImpl;
import com.shop.product.dao.ProductDaoImpl;

@Service
public class OrderServiceImpl {
	@Resource
	private OrderDaoImpl orderDaoImpl;
	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	public void addOrder(Order order) {
		orderDaoImpl.saveOrder(order);
	}
	
	//生成一个CartItem集合的集合，即订单集合
	//生成一个分页对象
	//在request里放一个curorder，代表当前显示订单
	public void personal(HttpSession session, HttpServletRequest request) {
		User u = (User) session.getAttribute("user");
		Set<Order> set = u.getOrderSet();
		
		List<List<CartItem>> olist = new ArrayList<>();
		
		//获取一个订单列表
		for(Order o : set) {
			String info = o.getInfo();
			List<CartItem> items = new ArrayList<>();
			for(String s : info.split(" ")) {
				String[] item = s.split("\\=");
				int id = Integer.parseInt(item[0]);
				int num = Integer.parseInt(item[1]);
				
				//创建一个购物车项
				CartItem cartItem = new CartItem();
				cartItem.setPro(productDaoImpl.findById(id));
				cartItem.setNum(num);
				
				//加入到集合里去
				items.add(cartItem);
			}
			olist.add(items);
		}
		
		//把olist放到session里去
		session.setAttribute("orderlist", olist);
		//curorder
		request.setAttribute("curorder", olist.get(0));
		//当前订单的总价
		int total = 0;
		for(CartItem c : olist.get(0)) {
			total += c.getNum() * c.getPro().getPrice();
		}
		request.setAttribute("total", total);
		
		//分页
		Page page = new Page(olist.size(), 1);
		session.setAttribute("page", page);
	}
	
	//改变当前显示订单
	//改page、total、curorder
	public String changeOrder(HttpSession session, HttpServletRequest request, int currentPage) {
		Page page = (Page) session.getAttribute("page");
		page.setCurrentPageNum(currentPage);
		if(currentPage < page.getPageNum())	page.setNextPageNum(currentPage + 1);
		else page.setNextPageNum(currentPage);
		if(currentPage > 1)	page.setPrePageNum(currentPage - 1);
		else page.setPrePageNum(currentPage);
		session.setAttribute("page", page);
		
		List<List<CartItem>> olist = (List<List<CartItem>>) session.getAttribute("orderlist");
		List<CartItem> items = olist.get(currentPage - 1);
		session.setAttribute("curorder", items);
		
		int total = 0;
		for(CartItem c : items) {
			total += c.getNum() * c.getPro().getPrice();
		}
		request.setAttribute("total", total);
		
		String top = "<h4>My Order</h4>";
		String middle = "";
		for(CartItem c : items) {
			double subTotal = c.getPro().getPrice() * c.getNum();
			middle += "<p>" + c.getPro().getName() + ": $"+c.getPro().getPrice()+" * "+c.getNum()+" = $"+subTotal+"</p>";
		}
		String bottom = "<h4>Total : $"+total+".0</h4>";
		//实在想不出好招了
		return top + middle + bottom;
	}
}
