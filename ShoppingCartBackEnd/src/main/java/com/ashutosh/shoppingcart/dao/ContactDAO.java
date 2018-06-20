package com.ashutosh.shoppingcart.dao;

import java.util.List;

import com.ashutosh.shoppingcart.domain.Contact;

public interface ContactDAO {
	public	boolean  saveContact(Contact contact);
	public List<Contact> list(); 

}
