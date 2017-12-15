package com.shop.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="users")
public class User {
	private String mail;      //邮箱，也是登录账号
	private String name;      //昵称
	private String password;
	private String info;  			//个人信息    
	private String address;	        //收货地址
	private String iconPath;	  //头像图片对应文件名
	private int activated;		//1为未激活       2为已激活
	
	//这里用到了hibernate的一对多，这样逻辑写起来也会方便一些
	private Set<Order> orderSet = new HashSet<>();
	
	//这里用到了hibernate的一对一，当然是为了方便才配的
	private Cart cart;
	
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public int getActivated() {
		return activated;
	}
	public void setActivated(int activated) {
		this.activated = activated;
	}
	@Id
	@GeneratedValue(generator = "my_gen")
	@GenericGenerator(name = "my_gen", strategy = "foreign", parameters =  @Parameter(name = "property", value = "cart"))
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	@OneToMany(mappedBy="user", targetEntity=Order.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public Set<Order> getOrderSet(){
		return orderSet;
	}
	@OneToOne(mappedBy="user")
	public Cart getCart() {
		return cart;
	}
}
