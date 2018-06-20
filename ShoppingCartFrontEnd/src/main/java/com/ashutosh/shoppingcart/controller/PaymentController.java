package com.ashutosh.shoppingcart.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ashutosh.shoppingcart.dao.CartDAO;
import com.ashutosh.shoppingcart.dao.OrderDAO;
import com.ashutosh.shoppingcart.dao.PaymentDAO;
import com.ashutosh.shoppingcart.domain.CartItem;
import com.ashutosh.shoppingcart.domain.OrderDetail;
import com.ashutosh.shoppingcart.domain.Payment;

@Controller
public class PaymentController 
{
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	OrderDAO orderDAO;

	@Autowired
	PaymentDAO paymentDAO;
	
	@Autowired 
	Payment payment;
	
	@RequestMapping(value="orderConfirm")
	public String showOrderConfirm(HttpSession session,Model m)
	{
		String username=(String)session.getAttribute("username");
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",listCartItems.size());
		
		return "OrderConfirm";
	}
	
	@RequestMapping(value="paymentConfirmation")
	public String paymentConfirm(@RequestParam("mode")String paymentMode,HttpSession session,Model m)
	{
		String username=(String)session.getAttribute("username");
		List<CartItem> listCartItems=cartDAO.listCartItems(username);
		
		m.addAttribute("listCartItems", listCartItems);
		m.addAttribute("totalCost", this.calculateTotalCost(listCartItems));
		session.setAttribute("CartItems",0);
		
	
		
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setPurchaseValue(this.calculateTotalCost(listCartItems));
		orderDetail.setPaymentMode(paymentMode);
		orderDetail.setUsername(username);
		orderDetail.setOrderDate(new java.util.Date());
		orderDAO.receiptGenerate(orderDetail);
		
		session.setAttribute("orderId", orderDetail.getOrderId());
		session.setAttribute("orderdate",orderDetail.getOrderDate());
		
		
	
		return "payment";
	}
	

	@PostMapping("/savepayment")
	public ModelAndView savePayment(
			@RequestParam int cardNumber, 
			@RequestParam Date expirationDate,
			@RequestParam int cvCode,
			@RequestParam String couponCode,
			@RequestParam String name
			
			) {

		ModelAndView mv = new ModelAndView("Receipt");
		payment.setCardNumber(cardNumber);
		payment.setExpirationDate(expirationDate);
		payment.setCvCode(cvCode);
		payment.setCoponCode(couponCode);
		payment.setName(name);
		//String value should be converted into integer
		
		// need to write one more condition.
		if (paymentDAO.save(payment)) {
			mv.addObject("msg", "payment done Successfully");
			return mv;
		} else {
			mv.addObject("msg", "problem in payment process");

		}
		return mv;
	
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
