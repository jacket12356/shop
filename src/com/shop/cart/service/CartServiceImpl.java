package com.shop.cart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.cart.dao.CartDaoImpl;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.order.dao.OrderDaoImpl;
import com.shop.product.dao.ProductDaoImpl;
import com.shop.user.dao.UserDaoImpl;

@Service
public class CartServiceImpl {
	@Resource
	private CartDaoImpl cartDaoImpl;
	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	@Resource
	private OrderDaoImpl orderDaoImpl;
	
	@Resource
	private UserDaoImpl userDaoImpl;
	
	//添加收货地址
	public void addAddress(HttpServletRequest request, HttpSession session) {
		User u = (User) session.getAttribute("user");
		String address = request.getParameter("address");
		u.setAddress(address);
		userDaoImpl.updateAddress(address, u.getMail());
	}
	
	//生成订单
	public void generateOrder(HttpSession session) {
		List<CartItem> list = (List<CartItem>) session.getAttribute("cartlist");
		User u = (User) session.getAttribute("user");
		Set<Order> oset = u.getOrderSet();
		String info = "";
		
		//整成info并存到一个Order对象里，Order对象要存到数据库里，还要把User的cart置空
		for(CartItem item : list) {
			int pid = item.getPro().getId();
			int pnum = item.getNum();
			String cat = pid + "=" + pnum;
			if(info.equals(""))	info += cat;
			else info += " " + cat;
		}
		
		
		Order o = new Order();
		o.setUser(u);
		o.setInfo(info);
		oset.add(o);
		orderDaoImpl.saveOrder(o);
		
		Cart c = u.getCart();
		info = "";
		c.setInfo(info);
		cartDaoImpl.updateCart(c);
		
		u.setCart(c);
		u.setOrderSet(oset);
		userDaoImpl.updateUser(u);
	}
	
	//删除购物车内的选中项
	public void deleteItem(String[] chk, HttpSession session) {
		List<CartItem> list = (List<CartItem>) session.getAttribute("cartlist");
		List<CartItem> delete = new ArrayList<>();
		String info = "";
		//删掉选中项
		for(String s : chk) {
			int id = Integer.parseInt(s);
			for(CartItem item : list) {
				if(item.getPro().getId() == id) {
					delete.add(item);
				}
			}
		}
		for(CartItem item : delete) {
			list.remove(item);
		}
		//整成info并更新
		for(CartItem item : list) {
			int pid = item.getPro().getId();
			int pnum = item.getNum();
			String cat = pid + "=" + pnum;
			if(info.equals(""))	info += cat;
			else info += " " + cat;
		}
		User u = (User) session.getAttribute("user");
		Cart c = u.getCart();
		c.setInfo(info);
		cartDaoImpl.updateCart(c);
	}
	
	//客户在结算页面修改了一个商品的购买数量
	public void changeNum(HttpServletRequest request, HttpSession session) {
		int id = Integer.parseInt(request.getParameter("productid"));
		int num = Integer.parseInt(request.getParameter("num"));
		String info = "";
		List<CartItem> list = (List<CartItem>) session.getAttribute("cartlist");
		for(CartItem item : list) {
			if(item.getPro().getId() == id) {
				item.setNum(num);
			}
		}
		
		//整成info并更新
		for(CartItem item : list) {
			int pid = item.getPro().getId();
			int pnum = item.getNum();
			String cat = pid + "=" + pnum;
			if(info.equals(""))	info += cat;
			else info += " " + cat;
		}
		User u = (User) session.getAttribute("user");
		Cart c = u.getCart();
		c.setInfo(info);
		cartDaoImpl.updateCart(c);
	}
	
	//用户转到购物车结算界面
	//这时候应该用info生成一个CartItem的集合
	//把这个集合存到session里
	public void cartCheckOut(HttpServletRequest request, HttpSession session) {
		List<CartItem> list = new ArrayList<>();
		User u = (User) session.getAttribute("user");
		Cart c = u.getCart();
		String info = c.getInfo();
		for(String s : info.split(" ")) {
			String[] item = s.split("\\=");
			int id = Integer.parseInt(item[0]);
			int num = Integer.parseInt(item[1]);
			
			//创建一个购物车项
			CartItem cartItem = new CartItem();
			cartItem.setPro(productDaoImpl.findById(id));
			cartItem.setNum(num);
			
			//加入到集合里去
			list.add(cartItem);
		}
		
		//集合加到request里去
		session.setAttribute("cartlist", list);
	}
	
	//清空购物车
	public void emptyInfo(HttpSession session) {
		User u = (User) session.getAttribute("user");
		Cart c = u.getCart();
		String info = c.getInfo();
		info = "";
		c.setInfo(info);
		cartDaoImpl.updateCart(c);
	}
	
	//创建一个购物车
	public void addCart(Cart cart) {
		cartDaoImpl.saveCart(cart);
	}
	
	//用户点击"add to cart",这一点用ajax弄
	//按现在的情况看，可能用户每修改一下购物情况，我就要把它更新到数据库
	public void addProduct(int productId, HttpSession session) {
		User u = (User) session.getAttribute("user");
		Cart c = u.getCart();
		String info = c.getInfo();
		int index = info.indexOf(productId + "");
		if(index == -1) {
			if(info.equals("")) {
				info += productId + "=1";
				c.setInfo(info);
			}else {
				info += " " + productId + "=1";
				c.setInfo(info);
			}
			//必须得更新到数据库
			cartDaoImpl.updateCart(c);
		}else {
			String cat = info.substring(index, index + 3);
			String[] cats = cat.split("\\=");
			String newCat = cats[0] + "=" + (Integer.parseInt(cats[1]) + 1);
			String newInfo = info.replace(cat, newCat);
			c.setInfo(newInfo);
			//必须得更新到数据库
			cartDaoImpl.updateCart(c);
		}
		
	}
}
