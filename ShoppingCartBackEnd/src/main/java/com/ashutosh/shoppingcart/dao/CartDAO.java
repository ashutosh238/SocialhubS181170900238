package com.ashutosh.shoppingcart.dao;

import java.util.List;

import com.ashutosh.shoppingcart.domain.CartItem;

public interface CartDAO 
{
	public boolean addToCart(CartItem cartItem);
	public boolean deleteFromCart(CartItem cartItem);
	public boolean updateCart(CartItem cartItem);
	public CartItem getCartItem(int cartItemId);
	public List<CartItem> listCartItems(String emailID);
}
