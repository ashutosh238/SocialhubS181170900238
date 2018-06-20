package com.ashutosh.shoppingcart.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ashutosh.shoppingcart.dao.ProductDAO;
import com.ashutosh.shoppingcart.domain.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Product product;
	
	@Autowired
	private HttpSession httpSession;
	@RequestMapping(value="/productPage",method=RequestMethod.GET)
	public String displayProductPage(Model m)
	{
		List<Product> listProducts=productDAO.list();
		m.addAttribute("listProducts", listProducts);
		
		return "ProductPage";
	}

	@PostMapping("product/save")
	public ModelAndView saveProduct(
			@RequestParam String name, 
			@RequestParam String description,
			@RequestParam String price,
			@RequestParam String id,
			@RequestParam String cateoryName,
			@RequestParam MultipartFile productImage
			) {

		ModelAndView mv = new ModelAndView("redirect:/manage_products");
		product.setDescription(description);
		product.setName(name);
		product.setId(id);
		product.setCategory_name(cateoryName);
		//String value should be converted into integer
		
		product.setPrice(Integer.parseInt(price));
		
		// need to write one more condition.
		if (productDAO.save(product)) {
			//mv.addObject("message", "Product created Successfully");
			//return mv;
		} else {
			mv.addObject("message", "Could not create product.");

		}
		String path="C:\\Users\\Ashutosh\\eclipse-workspace\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\";
		path=path+String.valueOf(product.getId())+".jpg";
		File filepath=new File(path);
		
		if(!productImage.isEmpty())
		{
			try
			{
				byte[] buffer=productImage.getBytes();
				FileOutputStream fos=new FileOutputStream(filepath);
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				bs.write(buffer);
				bs.close();
				mv.addObject("message", "Product created ndklgnklfn Successfully");
			}
			catch(Exception e)
			{
				mv.addObject("errorInfo", "There is Exception:"+e.getMessage());
			}
		}
		else
		{
			mv.addObject("errorInfo", "There is System Problem");
		}
		
		return mv;

	}
	
	@RequestMapping(value="/productDesc/{id}",method=RequestMethod.GET)
	public String displayProductDesc(@PathVariable("id")String id,Model m)
	{
		Product product=(Product)productDAO.get(id);
		m.addAttribute("ProductInfo", product);
		return "ProductDesc";
	}
	
	
	
	@GetMapping("/product/delete")
	public ModelAndView deleteProduct(@RequestParam String id)
	{
		ModelAndView mv = new ModelAndView("redirect:/manage_products");
		
		if(productDAO.delete(id))
		{
			mv.addObject("message", "The product successfully deleted");
		}
		else
		{
			mv.addObject("message", "Could not delete the product.  Please try after some time.");
		}
		return mv;
	}
	
	
	
	
	@GetMapping("/product/edit/")
	public String editProduct(@RequestParam String id)
	{
		//ModelAndView mv = new ModelAndView("redirect:/manage_products");
		product = productDAO.get(id);
		
		//mv.addObject("product", product);
		httpSession.setAttribute("selectedProduct", product);
		
		//why we need to have mv as we are using httpSession;
		
		//return mv;
		
		return "redirect:/manage_products";
	}
	
	@RequestMapping("search")
	public String getProductById(@RequestParam("category_name") String category_name,Model model){
		 List<Product> products=productDAO.searchByCategory(category_name);
		model.addAttribute("products",products);
		products.size();
		return "viewport";
		
	}
	
	
	
	
	
	

}
