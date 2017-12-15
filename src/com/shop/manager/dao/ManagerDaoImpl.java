package com.shop.manager.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shop.entity.SuperUser;

@Repository("managerDao")  
public class ManagerDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	public void saveSUser(SuperUser su) {
		this.sessionFactory.getCurrentSession().save(su);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public SuperUser findById(String account) {
		return this.sessionFactory.getCurrentSession().get(SuperUser.class, account);
	}
}
