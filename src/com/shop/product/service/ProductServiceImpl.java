package com.shop.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.entity.Product;
import com.shop.entity.Type;
import com.shop.product.dao.ProductDaoImpl;

@Service
public class ProductServiceImpl {
	@Resource
	private ProductDaoImpl productDaoImpl;
	//商品类型生数据录入
	public void addType(Type t) {
		productDaoImpl.saveType(t);
	}
	//商品类型生数据录入
	public void deleteTypes() {
		productDaoImpl.deleteAll();
	}
	//商品类型生数据录入
	public List<Type> getAllTypes(){
		return productDaoImpl.findAll();
	}
	//商品生数据录入
	public void addProducts(List<Product> list) {
		productDaoImpl.saveProducts(list);
	}
	//获取新商品
	public List<Product> getNewProducts(){
		return productDaoImpl.findByNewId();
	}
	
	//获取某一类商品
	public List<Product> getTypedProducts(int typeId){
		return productDaoImpl.findTypeId(typeId);
	}
	
	//删除所有商品
	public void dropAllProducts() {
		productDaoImpl.deleteAllProducts();
	}
	
	//获取最热商品
	public List<Product> getHotProducts(){
		return productDaoImpl.findByHotId();
	}
	
	//商品搜索功能
	public List<Product> getSearchResult(String keyWords){
		List<Product> all = productDaoImpl.findAllProduct();
		List<Product> list = new ArrayList<>();
		
		for(Product p : all) {
			if(p.getName().contains(keyWords)) {
				list.add(p);
			}
		}
		
		return list;
	}
}
