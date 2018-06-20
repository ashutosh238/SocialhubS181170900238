package com.ashutosh.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ashutosh.shoppingcart.dao.CategoryDAO;
import com.ashutosh.shoppingcart.dao.ContactDAO;
import com.ashutosh.shoppingcart.dao.UserDAO;
import com.ashutosh.shoppingcart.domain.Category;
import com.ashutosh.shoppingcart.domain.Contact;
import com.ashutosh.shoppingcart.domain.Product;
import com.ashutosh.shoppingcart.domain.User;
import com.ashutosh.shoppingcart.dao.ProductDAO;

//convert this class into Servlet / Controller
@Controller
public class HomeController {
	
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@Autowired
	private User user;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private Contact contact;
	//we have to define handler mapping
	//Different types of mappings
	//@GetMapping - doGet
	//@PostMapping - doPost  - create
	//@PutMapping	-doPut   - update
	//@DeleteMapping - doDelete - delete
	
	
	//   http://localhost:8080/shoppingcart/
		@GetMapping("/")
	//@RequestMapping(value="/",   method = RequestMethod.GET)
	public ModelAndView homePage()
	{ModelAndView mv = new ModelAndView("home");
			List<Product> listProducts=productDAO.list();
			mv.addObject("listProducts", listProducts);
			//return "ProductPage";
			
			//get all the categories categoryDAO.list()
		List<Category> categories=	categoryDAO.list();
		
		//will be available only in HomeController and Home.jsp
		mv.addObject("categories", categories);
		
		//categories should be available in all resources
		//httpSession.setAttribute("categories", categories);
		return mv;
		
			
	}
		
	//http://localhost:8080/shoppingcart/login	
	@GetMapping("/login")	
	public ModelAndView login()
	{
		ModelAndView mv = new ModelAndView("home");
		//mv.addObject("loginMessage", "Thank for login");
		mv.addObject("isUserClickedLogin", true);
		return mv;

	}
	//http://localhost:8080/shoppingcart/register
		@GetMapping("/register")	
		
	public ModelAndView registration()
	{
			//if the user click register, this method will call
			//--discuss tomorrow.
			//Model,  ModelAndView
			//navigate to home page
		ModelAndView mv= new ModelAndView("home");	
		
		//carry the data
		//mv.addObject("msg", "Thank you for registration");
		mv.addObject("isUserClickedRegister", true);
			
		
		return mv;
	}
		
		@GetMapping("/contact")	
		
		public ModelAndView contact()
		{
				//if the user click register, this method will call
				//--discuss tomorrow.
				//Model,  ModelAndView
				//navigate to home page
			ModelAndView mv= new ModelAndView("home");	
			
			//carry the data
			//mv.addObject("msg", "Thank you for registration");
			mv.addObject("isUserClicked", true);
				
			
			return mv;
		}
		
		@PostMapping("/saveGriviance")
		public ModelAndView saveGriviance(
				@RequestParam String emailID, 
				@RequestParam String name,
				@RequestParam String subject,
				@RequestParam String message
				
				
				) {
			contact.setEmailID(emailID);
			contact.setMessage(message);
			contact.setName(name);
			contact.setSubject(subject);
			ModelAndView mv = new ModelAndView("home");
			
			//String value should be converted into integer
			
			// need to write one more condition.
			if (contactDAO.saveContact(contact)) {
				mv.addObject("msg", "Complaint genereted successfully");
				return mv;
			} else {
				mv.addObject("msg", "problem in generating complaint");

			}
			return mv;
		
	}
	

		
		
		@PostMapping("/save")
		public ModelAndView saveUser(
				@RequestParam String emailID, 
				@RequestParam String name,
				@RequestParam String mobile,
				@RequestParam String address,
				//@RequestParam Character role,
				@RequestParam String password,
				@RequestParam String ConfirmPassword
				
				) {

			ModelAndView mv = new ModelAndView("home");
			user.setEmailID(emailID);
			user.setName(name);
			user.setMobile(mobile);
			user.setAddress(address);
			user.setRole('C');
			user.setPassword(password);
			//String value should be converted into integer
			
			// need to write one more condition.
			if (userDAO.save(user)) {
				mv.addObject("msg", "user created Successfully :"+name);
				return mv;
			} else {
				mv.addObject("msg", "Could not create product.");

			}
			return mv;
		
	}
		
		
		
		
	
	
	
	@GetMapping("/logout")
	public ModelAndView logout()
	{
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("msg", "You successfully logged out from the app");
		//we need to write some other code to 
		//do actual logout functionality.
		//will add later.
		
		return mv;
	}
	
	
	
	
	
	
	
	
	

}
