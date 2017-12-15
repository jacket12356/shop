package com.shop.manager.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.cart.dao.CartDaoImpl;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.Product;
import com.shop.entity.SuperUser;
import com.shop.entity.Type;
import com.shop.entity.User;
import com.shop.manager.dao.ManagerDaoImpl;
import com.shop.order.dao.OrderDaoImpl;
import com.shop.product.dao.ProductDaoImpl;
import com.shop.user.dao.UserDaoImpl;

@Service
public class ManagerServiceImpl {
	@Resource
	private ManagerDaoImpl managerDaoImpl;
	
	@Resource
	private UserDaoImpl userDaoImpl;
	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	@Resource
	private OrderDaoImpl orderDaoImpl;
	
	@Resource
	private CartDaoImpl cartDaoImpl;
	
	public void addUser(String account, String password) {
		SuperUser su = new SuperUser();
		su.setAccount(account);
		su.setPassword(password);
		managerDaoImpl.saveSUser(su);
	}
	
	//登录
	public boolean login(String account, String password) {
		SuperUser su = managerDaoImpl.findById(account);
		if(password.equals(su.getPassword())) return true;
		return false;
	}
	
	//商品列表
	public void productList(HttpServletRequest request) {
		List<Product> list = productDaoImpl.findAllProduct();
		request.setAttribute("productlist", list);
	}
	
	//商品查询
	public void search(String keyWord ,HttpServletRequest request) {
		List<Product> list = productDaoImpl.findAllProduct();
		List<Product> newList = new ArrayList<>();
		for(Product p : list) {
			if(p.getName().contains(keyWord))	newList.add(p);
		}
		request.setAttribute("productlist", newList);
	}
	
	//跳转商品添加
	public void addproductDetail(HttpServletRequest request) {
		List<Type> list = productDaoImpl.findAll();
		request.setAttribute("types", list);
	}
	
	//添加商品
	public void addProduct(MultipartFile file, HttpServletRequest request) {
		Product p = new Product();
		p.setName(request.getParameter("name"));
		p.setPrice(Double.parseDouble(request.getParameter("price")));
		p.setTypeId(Integer.parseInt(request.getParameter("type")));
		//recommend
		int rec = Integer.parseInt(request.getParameter("recommend"));
		if(rec == 1) {
			p.setIsHot(2); 
			p.setIsNew(1);
		}else {
			p.setIsHot(1);
			p.setIsNew(2);
		}
		p.setPhoto(file.getOriginalFilename());
		p.setInfo(request.getParameter("info"));
		
		productDaoImpl.addProduct(p);
	}
	
	public boolean iconUpload(MultipartFile file, HttpServletRequest request) {
		byte[] icon;
    	if(!file.isEmpty()) {
    		try {
    			//将头像图片存至/images/下
    			icon = file.getBytes();
    			String realFile = request.getServletContext().getRealPath("/") + "images/" + file.getOriginalFilename();
    			OutputStream output = new FileOutputStream(new File(realFile));
    			output.write(icon);
				
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return false;
	}
	
	//跳转商品编辑
	public void changeproductDetail(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Type> list = productDaoImpl.findAll();
		Product p = productDaoImpl.findById(id);
		request.setAttribute("types", list);
		request.setAttribute("p", p);
	}
	
	//编辑商品
	public void changeProduct(MultipartFile file, HttpServletRequest request) {
		Product p = new Product();
		p.setName(request.getParameter("name"));
		p.setPrice(Double.parseDouble(request.getParameter("price")));
		p.setTypeId(Integer.parseInt(request.getParameter("type")));
		//recommend
		int rec = Integer.parseInt(request.getParameter("recommend"));
		if(rec == 1) {
			p.setIsHot(2); 
			p.setIsNew(1);
		}else {
			p.setIsHot(1);
			p.setIsNew(2);
		}
		p.setPhoto(file.getOriginalFilename());
		p.setInfo(request.getParameter("info"));
		
		productDaoImpl.updateProduct(p);
	}
	
	//删除商品
	public void deleteProduct(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		productDaoImpl.deleteById(id);
	}
	
	//订单列表
	public void orderList(HttpServletRequest request) {
		List<Order> olist = orderDaoImpl.findAll();
		List<OrderItem> oitem = new ArrayList<>();
		for(Order o : olist) {
			OrderItem item = new OrderItem();
			item.setId(o.getId());
			int total = 0;
			
			for(String s : o.getInfo().split(" ")) {
				String[] items = s.split("\\=");
				int id = Integer.parseInt(items[0]);
				int num = Integer.parseInt(items[1]);
				Product p = productDaoImpl.findById(id);
				total += num * p.getPrice();
			}
			item.setTotal(total);
			
			item.setName(o.getUser().getName());
			
			oitem.add(item);
		}
		request.setAttribute("orderlist", oitem);
	}
	
	//订单详情
	public void orderDetail(int id, HttpServletRequest request) {
		Order o = orderDaoImpl.findById(id);
		List<CartItem> list = new ArrayList<>();
		
		for(String s : o.getInfo().split(" ")) {
			String[] item = s.split("\\=");
			int pid = Integer.parseInt(item[0]);
			int num = Integer.parseInt(item[1]);
			
			//创建一个购物车项
			CartItem cartItem = new CartItem();
			cartItem.setPro(productDaoImpl.findById(pid));
			cartItem.setNum(num);
			
			//加入到集合里去
			list.add(cartItem);
		}
		
		request.setAttribute("orderitems", list);
	}
	
	//删除订单
	public void deleteOrder(int id) {
		orderDaoImpl.deleteById(id);
	}
	
	//用户列表
	public void userList(HttpServletRequest request) {
		List<User> list = userDaoImpl.findAll();
		request.setAttribute("userlist", list);
	}
	
	//增加新用户
	public void addUser(MultipartFile file, HttpServletRequest request) {
		//上传头像
		byte[] icon;
    	if(!file.isEmpty()) {
    		try {
    			//将头像图片存至/icons/下
    			icon = file.getBytes();
    			String realFile = request.getServletContext().getRealPath("/") + "icons/" + file.getOriginalFilename();
    			OutputStream output = new FileOutputStream(new File(realFile));
    			output.write(icon);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//存基本信息
    	User u = new User();
    	u.setActivated(2);
    	u.setAddress(request.getParameter("address"));
    	Cart c = new Cart();
    	c.setMail(request.getParameter("mail"));
    	cartDaoImpl.saveCart(c);
    	u.setCart(c);
    	u.setIconPath(file.getOriginalFilename());
    	u.setInfo(request.getParameter("info"));
    	u.setMail(request.getParameter("mail"));
    	u.setName(request.getParameter("name"));
    	u.setPassword(request.getParameter("password"));
    	
    	userDaoImpl.saveUser(u);
	}
	
	//编辑用户跳转
	public void userDetail(String oid, HttpServletRequest request) {
		User u = userDaoImpl.findById(oid);
		request.setAttribute("u", u);
	}
	
	//编辑用户
	public void changeUser(MultipartFile file, HttpServletRequest request) {
		//上传头像
		byte[] icon;
    	if(!file.isEmpty()) {
    		try {
    			//将头像图片存至/icons/下
    			icon = file.getBytes();
    			String realFile = request.getServletContext().getRealPath("/") + "icons/" + file.getOriginalFilename();
    			OutputStream output = new FileOutputStream(new File(realFile));
    			output.write(icon);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//存基本信息
    	User u = new User();
    	u.setAddress(request.getParameter("address"));
    	u.setIconPath(file.getOriginalFilename());
    	u.setInfo(request.getParameter("info"));
    	u.setName(request.getParameter("name"));
    	u.setPassword(request.getParameter("password"));
    	
    	userDaoImpl.saveUser(u);
	}
	
	//删除用户
	public void deleteUser(String oid) {
		userDaoImpl.deleteById(oid);
	}
}
