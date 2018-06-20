package com.ashutosh.shoppingcart.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashutosh.shoppingcart.dao.ContactDAO;
import com.ashutosh.shoppingcart.domain.Contact;
import com.ashutosh.shoppingcart.domain.User;

@Repository("contactDAO")
@Transactional
public class ContactDAOImpl implements ContactDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public boolean saveContact(Contact contact) {
		// get session from Session Factory
		// from SessionFactory
		// 1) Open new Session
		// 2) continue with Current Session
		try {
			// set current date
			//user.setAdded_date(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().saveOrUpdate(contact);
		} catch (Exception e) {
			// print the complete exception stack trace
			e.printStackTrace();
			return false;
		}
		// above line : opening session saving user in user table.

		// TODO Auto-generated method stub
		return true;
	}

	public List<Contact> list() {
		return sessionFactory.getCurrentSession().createQuery("from Contact").list();
	}


	
}
