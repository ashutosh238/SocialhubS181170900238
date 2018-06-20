package com.ashutosh.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ashutosh.shoppingcart.dao.CategoryDAO;
import com.ashutosh.shoppingcart.dao.ContactDAO;
import com.ashutosh.shoppingcart.dao.ProductDAO;
import com.ashutosh.shoppingcart.dao.UserDAO;
import com.ashutosh.shoppingcart.domain.Category;
import com.ashutosh.shoppingcart.domain.Contact;
import com.ashutosh.shoppingcart.domain.Product;
import com.ashutosh.shoppingcart.domain.User;

@Controller
public class AdminController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Product product;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private Contact contact;
	
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/manage_categories")
	public ModelAndView manageCategories()
	{
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageCategories", true);
		//load all the categories and set to httpSession
		List<Category> categories =  categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		
		return mv;
	}
	
	@GetMapping("/manage_suppliers")
	public ModelAndView manageSuppliers()
	{
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageSupplires", true);
		
		List<User> suppliers = userDAO.list('S');
		httpSession.setAttribute("suppliers", suppliers);
		
		return mv;
	}
	
	@GetMapping("/manage_products")
	public ModelAndView manageProducts()
	{
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageProducts", true);
		
		List<Category> categories =  categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		
		List<Product> products =  productDAO.list();
		httpSession.setAttribute("products", products);

		
		return mv;
	}

	@RequestMapping(value="/chk_query",method=RequestMethod.GET)
	public String displayContact(Model m)
	{
		List<Contact> listProducts=contactDAO.list();
		m.addAttribute("listProducts", listProducts);
		
		return "ContactPage";
	}
}





