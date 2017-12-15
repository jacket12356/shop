package com.shop.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Product;
import com.shop.entity.Type;
import com.shop.product.service.ProductServiceImpl;

@Controller  
public class ProductController {
	@Resource
	private ProductServiceImpl productServiceImpl;
	
	//主页跳转
	//提供一个商品类别集合和一个最新商品集合(这两个集合存在session里)
	//因为要传递的参数都与product有关，所以要放在这里
	@RequestMapping("/index")
	public String index(HttpSession session) {
		List<Type> typeList = productServiceImpl.getAllTypes();    //获取所有商品类型
		List<Product> newProductList = productServiceImpl.getNewProducts();     //获取最新商品
		List<Product> hotProductList = productServiceImpl.getHotProducts();
		
		session.setAttribute("typelist", typeList);
		session.setAttribute("newproductlist", newProductList);
		session.setAttribute("hotproductlist", hotProductList);
		
		return "redirect:index.jsp";
	}
	
	//根据商品类型展示商品列表
	//我感觉这次的productlist应该存到request里
	@RequestMapping("/productlist")
	public String productList(@RequestParam(value="typeid",required=true) int typeId, HttpServletRequest request) {
		List<Product> productList = productServiceImpl.getTypedProducts(typeId);
		request.setAttribute("productlist", productList);
		
		return "products";
	}
	
	//搜索功能
	//从service那里得到符合条件的product，就将它们展示到products.jsp页面上
	@RequestMapping("/search")
	public String Search(@RequestParam(value="keywords",required=true) String keyWords,
			HttpServletRequest request) {
		List<Product> list = productServiceImpl.getSearchResult(keyWords);
		request.setAttribute("productlist", list);
		return "products";
	}
	
	//商品类型生数据录入，测试用
//	@RequestMapping("/deletetypes")
//	public String deleteTypes() {
//		productServiceImpl.deleteTypes();
//		return "index";
//	}
//	
//	@RequestMapping("/addtypes")
//	public String addTypes() {
//		
//		String[] types = {"","men","women","accesories","footwear","underwear & socks"};
//		
//		
//		for(int i = 2 ; i <= 5 ; i ++) {
//			Type t = new Type();
//			t.setId(i);
//			t.setName(types[i]);
//			productServiceImpl.addType(t);
//		}
//		
//		return "index";
//	}
	
	//商品生数据录入，测试用
//	@RequestMapping("/addproducts")
//	public String addProducts() {
//		List<Product> list = new ArrayList<>();
//		int[] typeid = {0,1,1,1,2,1,1,2,2,1,2,1,2};
//		String[] name = {"","brown sports wear","pink dress","NIKE's T-shirt","blue shirt",
//				"palm 95 shirt","shoe pattern t-shirt","jeans","pink sports wear","yellow sports wear",
//				"purple dress","white T-shirt","black dressing gown"};
//		int[] ishot = {0,1,1,1,1,1,1,1,1,2,2,2,2};
//		int[] isnew = {0,1,2,2,1,1,2,2,1,1,1,1,1};
//		String[] photo = {"","pi.png","pi1.png","pi2.png","pi3.png","pi4.png","pi5.png",
//				"pi6.png","pi7.png","pr.jpg","pr1.jpg","pr2.jpg","pr3.jpg"};
//		
//		//删除所有商品
//		productServiceImpl.dropAllProducts();
//		
//		for(int i = 1 ; i <= 12 ; i ++) {
//			Product p = new Product();
//			double d = 100 + i * 20;
//			p.setId(i);
//			p.setIsHot(ishot[i]);
//			p.setIsNew(isnew[i]);
//			p.setName(name[i]);
//			p.setPhoto(photo[i]);
//			p.setPrice(d);
//			p.setTypeId(typeid[i]);
//			list.add(p);
//		}
//		productServiceImpl.addProducts(list);
//		
//		return "redirect:index.jsp";
//	}
}
