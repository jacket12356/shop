package com.shop.order.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shop.entity.Order;

@Repository("orderDao")
public class OrderDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	public void saveOrder(Order order) {
		sessionFactory.getCurrentSession().save(order);
		sessionFactory.getCurrentSession().flush();
	}
	
	public List<Order> findAll() {
		String hql = "from Order";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public Order findById(int id) {
		Order o = this.sessionFactory.getCurrentSession().get(Order.class, id);
		return o;
	}
	
	public void deleteById(int id) {
		String hql = "delete from Order where id=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}
}
