package com.ashutosh.shoppingcart;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ashutosh.shoppingcart.dao.ContactDAO;
import com.ashutosh.shoppingcart.domain.Contact;

public class ContactDAOTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static ContactDAO contactDAO;
	
	private static Contact contact;
	
	
	@BeforeClass
	public static void initialize()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.ashutosh");
		context.refresh();
		
		contactDAO = (ContactDAO)context.getBean("contactDAO");
		contact = (Contact) context.getBean("contact");
				
	}
	
	
	@Test
	public void saveContactTestCase()
	{
		contact = new Contact();
		contact.setEmailID("abc");
		contact.setMessage("hii");
		contact.setSubject("sub");
		contact.setName("name");
			
		
		boolean result = contactDAO.saveContact(contact);
		Assert.assertEquals("save contact",true,result );
	}
	
	
	@Test
	public void getAllUsers()
	{
		int size = contactDAO.list().size();
		
		Assert.assertEquals(1, size);
	}	

	

}
