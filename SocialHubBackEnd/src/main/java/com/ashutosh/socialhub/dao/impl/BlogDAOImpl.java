package com.ashutosh.socialhub.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashutosh.socialhub.dao.BlogDAO;
import com.ashutosh.socialhub.domain.Blog;


@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Blog blog) {
		 
			
			try {
				 
				blog.setCreateDate(new Date(System.currentTimeMillis()));
				sessionFactory.getCurrentSession().saveOrUpdate(blog);
			} catch (Exception e) {
				// print the complete exception stack trace
				e.printStackTrace();
				return false;
			}
			
			return true;
		
	}

	public boolean update(Blog blog) {
		try {
			 
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		 
	}

	public Blog get(int id) {
		Session session=sessionFactory.openSession();
		Blog blog=(Blog)session.get(Blog.class,id);
		session.close();
		return blog;
	}

	public List<Blog> list() {
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}

	public boolean delete(Blog blog) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
		
	}

}
