package com.ashutosh.shoppingcart.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashutosh.shoppingcart.dao.CategoryDAO;
import com.ashutosh.shoppingcart.dao.ProductDAO;
import com.ashutosh.shoppingcart.domain.Product;

///need to add  @Transactional
@Repository("productDAO")
@Transactional
public class ProductDAOImpl  implements ProductDAO{
	
	@Autowired
	private Product product;
	
	//Declare the SessionFactory -supposed automatically injected in the class
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Product product) {
		try {
			product.setAdded_date(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean update(Product product) {

		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	
		
	}

	public boolean delete(String name) {

		try {
			//if the product exist, then only delete
			Product product = get(name);
			if(product==null)
			{
				return false;
			}
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	
		
	}

	public Product get(String id) {
		
	try {
		product = (Product)sessionFactory.getCurrentSession().get(Product.class,id);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return product;
		
	}

	public List<Product> list() {
		
	return	sessionFactory.getCurrentSession().createQuery("from Product").list();
		
	}

	public List<Product> searchByCategory(String category_name) {
		
		return	(List<Product>) sessionFactory.getCurrentSession().createQuery("from Product where category_name=:category_name").setParameter("category_name",category_name).list();
			
		
		
	}

	
	
	
	
}









