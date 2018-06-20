package com.ashutosh.shoppingcart.dao;

import com.ashutosh.shoppingcart.domain.OrderDetail;

public interface OrderDAO
{
	public boolean receiptGenerate(OrderDetail orderDetail);
	public boolean updateCartItemStatus(String emailID);
}
