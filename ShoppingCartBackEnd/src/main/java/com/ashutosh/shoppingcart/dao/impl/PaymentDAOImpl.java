package com.ashutosh.shoppingcart.dao.impl;

import java.sql.Date;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashutosh.shoppingcart.dao.PaymentDAO;
import com.ashutosh.shoppingcart.domain.Payment;

@Repository("paymentDAO")
@Transactional
public class PaymentDAOImpl implements PaymentDAO {

	// Get the Sesion Factory
	@Autowired
	private SessionFactory sessionFactory;

	

	public boolean save(Payment payment) {
		// get session from Session Factory
		// from SessionFactory
		// 1) Open new Session
		// 2) continue with Current Session
		try {
			// set current date
			//user.setAdded_date(new Date(System.currentTimeMillis()));
			sessionFactory.getCurrentSession().saveOrUpdate(payment);
		} catch (Exception e) {
			// print the complete exception stack trace
			e.printStackTrace();
			return false;
		}
		// above line : opening session saving user in user table.

		// TODO Auto-generated method stub
		return true;
	}
}

