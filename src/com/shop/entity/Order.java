package com.shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="orders")
public class Order {
	private int id;
	private String info;	//订单表的每一行代表一个订单，订单信息按照这个格式存："id=num id=num id=num id=num ...."
	private User user;		//一对多
	
	
	//注意注解一定放到一处，要不全放在field上，要不就是全放到getter上，不能混放
	@Id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name = "my_gen", strategy = "increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	//这里用到了hibernate的一对多，这样逻辑写起来也会方便一些
	@ManyToOne
	@JoinColumn(name="mail")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
//	public static List<Product> getProductList(String info){
//		List<Product> list = new ArrayList<>();
//		for(String p : info.split(" ")) {
//			String[] s = p.split("\\=");
//			Product pro = new Product();
//			pro.setId(Integer);
//			pro.setName(s[1]);
//		}
//	}
}
