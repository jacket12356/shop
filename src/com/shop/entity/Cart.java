package com.shop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//其实可以把一个购物车看成一个订单，我建购物车表的目的是用户登陆后能看到他（她）上次没有结算的购物车
@Entity
@Table(name="carts")
public class Cart {
	private String mail;
	private String info = "";
	//这里用到了hibernate的一对一，当然是为了方便才配的
	private User user;
	
	
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="mail")
	public User getUser() {
		return user;
	}
}
