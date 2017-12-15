package com.shop.user.dao;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shop.entity.User;

@Repository("userDao")  
public class UserDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	public void deleteById(String mail) {
		String hql = "delete from User where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, mail);
		query.executeUpdate();
	}
	
	public List<User> findAll(){
		String hql = "from User";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public void updatePassword(String password, String mail) {
		String hql = "update User set password=? where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, password);
		query.setString(1, mail);
		query.executeUpdate();
	}
	
	public void updateInfo(String info, String mail) {
		String hql = "update User set info=? where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, info);
		query.setString(1, mail);
		query.executeUpdate();
	}
	
	public void updateAddress(String add, String mail) {
		//写hql语句用类名
		String hql = "update User set address=? where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, add);
		query.setString(1, mail);
		query.executeUpdate();
	}
	
	public void updateUser(User u) {
		this.sessionFactory.getCurrentSession().update(u);
		this.sessionFactory.getCurrentSession().flush();
	}

	public void saveUser(User u) {
		this.sessionFactory.getCurrentSession().save(u);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public User findById(String mail) {
		User u = this.sessionFactory.getCurrentSession().get(User.class, mail);
		return u;
	}
	
	//将activated字段改为2
	public void changeActivated(String mail) {
		//写hql语句用类名
		String hql = "update User set activated=2 where mail=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, mail);
		query.executeUpdate();
	}
}
