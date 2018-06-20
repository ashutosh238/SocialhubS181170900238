package com.ashutosh.shoppingcart.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Payment {

	@Id
	int cardNumber;
	Date expirationDate;
	int cvCode;
	String couponCode;
	String name;
	
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getCvCode() {
		return cvCode;
	}
	public void setCvCode(int cvCode) {
		this.cvCode = cvCode;
	}
	public String getCoponCode() {
		return couponCode;
	}
	public void setCoponCode(String coponCode) {
		this.couponCode = coponCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
