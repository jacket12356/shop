package com.shop.user.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.shop.cart.dao.CartDaoImpl;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.product.dao.ProductDaoImpl;
import com.shop.user.dao.UserDaoImpl;

@Service
public class UserServiceImpl {
	@Resource
	private UserDaoImpl userDaoImpl;
	
	@Resource
	private CartDaoImpl cartDaoImpl;
	
	@Resource
	private ProductDaoImpl productDaoImpl;
	
	//改密码
	public void changePassword(String password, String mail) {
		userDaoImpl.updatePassword(password, mail);
	}
	
	//改个人信息
	public void addInfo(String info, String mail) {
		userDaoImpl.updateInfo(info, mail);
	}
	
	//获取用户对象
	public User getUser(String mail) {
		return userDaoImpl.findById(mail);
	}
	
	//获取购物详情
	public String getShoppingInfo(String mail) {
		return cartDaoImpl.getInfo(mail);
	}
	
	//为用户创建一个购物车
	public Cart createCart(HttpServletRequest request) {
		Cart cart = new Cart();
		cart.setMail(request.getParameter("mail"));
		cartDaoImpl.saveCart(cart);
		return cart;
	}
	
//	//为用户创建一个订单列表
//	public Set<Order> createOrderList(HttpServletRequest request){
//		Set<Order> set = new HashSet<>();
//		
//	}
	
	//登录
	//这个功能除了要检查基本的账户密码还要检查账户是否激活
	//传递错误信息
	//在session中加入User对象以方便构建登录状态
	public boolean login(String mail, String password, HttpServletRequest request, HttpSession session) {
		User u = userDaoImpl.findById(mail);
		Cart c = u.getCart();
		
		if(u != null) {
			if(u.getActivated()== 1) {
				request.setAttribute("loginError", "warning: Account unactivated!");
				return false;
			}
			if(u.getPassword().equals(password)&&u.getActivated()==2) {
				//session加入User对象
				session.setAttribute("user", u);

		    	//登录时获取用户对应的购物车
		    	u.setPassword(password);
		    	session.setAttribute("cart", c);
		    	
		    	//登录时还要改一下购物车总价，不然不同账户登录总价会对不上号的
//				String info = c.getInfo();
//				List<CartItem> list = new ArrayList<>();
//				for(String s : info.split(" ")) {
//					String[] item = s.split("\\=");
//					int id = Integer.parseInt(item[0]);
//					int num = Integer.parseInt(item[1]);
//					
//					//创建一个购物车项
//					CartItem cartItem = new CartItem();
//					cartItem.setPro(productDaoImpl.findById(id));
//					cartItem.setNum(num);
//					
//					//加入到集合里去
//					list.add(cartItem);
//				}
//				int sum = 0;
//				for(CartItem item : list) {
//					sum += item.getPro().getPrice() * item.getNum();
//				}
//				String str1 = sum + "";
//				String su = "$" + str1 + ".00";
//		        request.setAttribute("sum", su);
				
				return true;
			}
		}
		request.setAttribute("loginError", "warning: Mail or password incorrect!");
		
		return false;
		
	}
	//检查用户填写的邮箱地址是否已经存在于数据库表中（用AJAX）
	public boolean isMailExisted(String mail) {
		User u = this.userDaoImpl.findById(mail);
		if(u == null) {
			return false;
		}
		return true;
	}
	//发送邮件
	public void sendMail(HttpServletRequest request) {
		//获取系统属性
		Properties props = System.getProperties();
		//配置邮件服务器
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		//获取会话
		javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("a291906254@163.com", "1996255820143");
			}
		});
		//构造消息
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("a291906254@163.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getParameter("mail")));
			message.setSubject("Complete Registratio");
			String[] m = request.getParameter("mail").split("@");
			String ma = m[0] + "-" + m[1];
			
			//这邮件qq不收
			String text = "Dear "+request.getParameter("name")+":\r\n" + 
					"	  \r\n" + 
					"	  Please click the link showed below to complete your registration:\r\n" + 
					"	  \r\n" + 
					"	  \r\n" + 
					"	  			http://localhost:8080/shop/activate?mail="+ma+"\r\n" + 
					"	  \r\n" + 
					"	  \r\n" + 
					"	  Lorem ipsum dolor sit amet, consectetur adipisicing elit,\r\n" + 
					"	  sed do eiusmod tempor incididunt ut labore et dolore magna \r\n" + 
					"	  aliqua. Ut enim ad minim veniam, quis nostrud exercitation \r\n" + 
					"	  ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis\r\n" + 
					"	  aute irure dolor in reprehenderit in voluptate velit esse \r\n" + 
					"	  cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat \r\n" + 
					"	  cupidatat non proident, sunt in culpa qui officia deserunt mollit \r\n" + 
					"	  anim id est laborum.";
			message.setText(text);
			message.setHeader("X-Mailer", "smtpsend");
			message.setSentDate(new Date());
			Transport.send(message);
		}catch(AddressException e) {
			e.printStackTrace();
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	//上传头像
	public boolean iconUpload(MultipartFile file, HttpServletRequest request) {
		byte[] icon;
    	if(!file.isEmpty()) {
    		try {
    			//将头像图片存至/icons/下
    			icon = file.getBytes();
    			String realFile = request.getServletContext().getRealPath("/") + "icons/" + file.getOriginalFilename();
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
	
	//在数据库表中加入基础的用户信息
	//邮箱、昵称、密码、未激活状态（1）、头像图片对应的文件名
	public void addUser(HttpServletRequest request, String fileName, Cart cart) {
		User u = new User();
		u.setName(request.getParameter("name"));
		u.setMail(request.getParameter("mail"));
		u.setPassword(request.getParameter("password"));
		u.setActivated(1);
		u.setIconPath(fileName);
		//这一行可别忘了，说实话为什么要这样我不懂
		u.setCart(cart);
		
		userDaoImpl.saveUser(u);
	}
	
	//激活账户
	public void activateAccount(String mail) {
		userDaoImpl.changeActivated(mail);
	}
}
