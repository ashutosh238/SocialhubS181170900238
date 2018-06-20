package com.ashutosh.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashutosh.shoppingcart.dao.CartDAO;
import com.ashutosh.shoppingcart.dao.ProductDAO;
import com.ashutosh.shoppingcart.domain.CartItem;
import com.ashutosh.shoppingcart.domain.Product;

@Controller
public class CartController 
{static int i=0;
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value="/showCart")
	public String showCart(Model m,HttpSession session)/*the Model.

Simply put, the model can supply attributes used for rendering views.

To provide a view with usable data, we simply add this data to its Model object. Additionally, maps with attributes can be merged with Model instances:*/
	{
		String username=(String)session.getAttribute("username");
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",listCartItems.size());
		
		return "Cart";
	}
	//@PathVariable used for accessing the values from the URI template.generate a URI from a template, retrieve a collection of variable names used in the template, determine whether two templates are equivalent, and return the template's string.
	@RequestMapping(value="/addToCart/{id}")
	public String addToCart(@PathVariable("id") String id,@RequestParam("quantity")int quantity,HttpSession session,Model m)
	{
		
		CartItem cartItem=new CartItem();
		Product product=productDAO.get(id);
		String username=(String)session.getAttribute("username");
		
		cartItem.setProductId(id);
		cartItem.setProductName(product.getName());
		cartItem.setQuantity(quantity);
		cartItem.setPrice(product.getPrice());
		cartItem.setStatus("NP");
		cartItem.setemailID(username);
		
		cartDAO.addToCart(cartItem);
		
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",listCartItems.size());
		
		return "Cart";
	}
	
	@RequestMapping(value="/deleteCartItem/{cartItemId}")
	public String deleteCartItem(@PathVariable("cartItemId")int cartItemId,Model m,HttpSession session)
	{
		
		CartItem cartItem=cartDAO.getCartItem(cartItemId);
		cartDAO.deleteFromCart(cartItem);
		
		String username=(String)session.getAttribute("username");
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",listCartItems.size());
		
		return "Cart";
	}
	
	@RequestMapping(value="/updateCartItem/{cartItemId}")
	public String updateCartItem(@PathVariable("cartItemId") int cartItemId,@RequestParam("qty") int quantity,Model m,HttpSession session)
	{

		CartItem cartItem=cartDAO.getCartItem(cartItemId);
		cartItem.setQuantity(quantity);
		cartDAO.updateCart(cartItem);
		
		String username=(String)session.getAttribute("username");
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",listCartItems.size());
		
		return "Cart";
		
	}
	public int calculateTotalCost(List<CartItem> cartItems)
	{
		int totalCost=0;
		int count=0;
		
		while(count<cartItems.size())
		{
			totalCost=totalCost+(cartItems.get(count).getPrice()*cartItems.get(count).getQuantity());
			count++;
		}
		
		return totalCost;
	}
}
