package com.ashutosh.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ashutosh.shoppingcart.dao.CartDAO;
import com.ashutosh.shoppingcart.dao.CategoryDAO;
import com.ashutosh.shoppingcart.domain.CartItem;

public class CartDAOTest 
{

	static CartDAO cartDAO;
	
	@BeforeClass
	public static void preExecution()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.ashutosh");
		context.refresh();
		
		cartDAO=(CartDAO)context.getBean("cartDAO");
	}
	
	@Test
	public void addCartTest()
	{
		CartItem cartItem=new CartItem();
		cartItem.setCartItemId(23);
		cartItem.setProductId("1003");
		cartItem.setProductName("OPPO F3");
		cartItem.setQuantity(10);
		cartItem.setStatus("NP");
		cartItem.setemailID("Ritesh@gmail.com");
		cartItem.setPrice(7000);
		
		assertTrue("Problem in Creating CartItem",cartDAO.addToCart(cartItem));
	}
}
