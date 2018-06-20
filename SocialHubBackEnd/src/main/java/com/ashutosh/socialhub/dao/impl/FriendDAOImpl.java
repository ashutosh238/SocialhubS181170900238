package com.ashutosh.socialhub.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashutosh.socialhub.dao.FriendDAO;
import com.ashutosh.socialhub.domain.Friend;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO{




		@Autowired
		private SessionFactory sessionFactory;
		public boolean save(Friend friend) {
			try {
				 
				//friend.set(new Date(System.currentTimeMillis()));
				sessionFactory.getCurrentSession().saveOrUpdate(friend);
			} catch (Exception e) {
				// print the complete exception stack trace
				e.printStackTrace();
				return false;
			}
			
			return true;
		
	}
		

		
		public boolean update(Friend friend) {
			try {
				 
				sessionFactory.getCurrentSession().update(friend);
				return true;
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
			
		}

		
		public boolean delete(Friend friend) {
			try
			{
				sessionFactory.getCurrentSession().delete(friend);
				return true;
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised:"+e);
				return false;
			}
			
		}

		
		public Friend get(int id) {
			Session session=sessionFactory.openSession();
			Friend friend=(Friend)session.get(Friend.class,id);
			session.close();
			return friend;
			
		}

		
		public List<Friend> list() {
			return sessionFactory.getCurrentSession().createQuery("from Friend").list();
		}

	}

