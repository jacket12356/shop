package com.shop.product.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.shop.entity.Product;
import com.shop.entity.Type;

@Repository("productDao")  
public class ProductDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	//更新商品
	public void updateProduct(Product p) {
		this.sessionFactory.getCurrentSession().update(p);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	//存商品
	public void addProduct(Product p) {
		this.sessionFactory.getCurrentSession().save(p);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	//存一些商品类型
	public void saveType(Type t) {
		this.sessionFactory.getCurrentSession().save(t);
		this.sessionFactory.getCurrentSession().flush();
	}
	
	public void deleteAll() {
		//写hql语句用类名
		String hql = "delete from Type";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	//获取数据库表中所有类型
	public List<Type> findAll() {
		String hql = "from Type";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	//存一些商品
	public void saveProducts(List<Product> list) {
		for(Product p : list) {
			this.sessionFactory.getCurrentSession().save(p);
		}
		this.sessionFactory.getCurrentSession().flush();
	}
	
	//删除所有商品
	public void deleteAllProducts() {
		String hql = "delete from Product";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	//获取所有最新商品
	public List<Product> findByNewId(){
		String hql = "from Product where isnew=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 2);
		return query.list();
	}
	
	
	//获取某一类型下所有商品
	public List<Product> findTypeId(int typeId){
		String hql = "from Product where typeid=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, typeId);
		return query.list();
	}
	
	//获取最热商品
	public List<Product> findByHotId(){
		String hql = "from Product where ishot=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, 2);
		return query.list();
	}
	
	//获取商品
	public Product findById(int id) {
		Product p = this.sessionFactory.getCurrentSession().get(Product.class, id);
		return p;
	}
	
	//获取所有商品
	public List<Product> findAllProduct(){
		String hql = "from Product";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	//删除单个商品(id)
	public void deleteById(int id) {
		String hql = "delete from Product where id=?";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
	}
}
