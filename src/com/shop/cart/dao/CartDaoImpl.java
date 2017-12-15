package com.shop.cart.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shop.entity.Cart;

@Repository("cartDao")
public class CartDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	//创建一个购物车
	public void saveCart(Cart cart) {
		sessionFactory.getCurrentSession().save(cart);
		sessionFactory.getCurrentSession().flush();
	}
	
	//更新购物情况
	public void updateCart(Cart cart) {
		sessionFactory.getCurrentSession().update(cart);
		sessionFactory.getCurrentSession().flush();
	}
	
	//获取购物情况
	public String getInfo(String mail) {
		//写hql语句用类名
		String hql = "select info from Cart where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, mail);
		return query.uniqueResult().toString();
	}
}
