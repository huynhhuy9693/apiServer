package com.fpt.main.services;



import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.main.advice.SendEmailService;
import com.fpt.main.dto.Purchase;
import com.fpt.main.dto.PurchaseResponse;
import com.fpt.main.entity.Customer;
import com.fpt.main.entity.Order;
import com.fpt.main.entity.OrderItem;
import com.fpt.main.reponsitory.CustomerRespository;



@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private SendEmailService sendEmailService;
	
	private CustomerRespository customerRespository;    
    private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC =  digits;
	private static Random generator = new Random();
	
	public CheckoutServiceImpl(CustomerRespository customerRespository) {
		this.customerRespository = customerRespository;
	}
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		int numberOfCharactor = 6;
		//retrieve the order info from dto
		Order order = purchase.getOrder();
		
		//generatr tracking number
		String orderTrackingNumber = generateOrderTrackingNumber(numberOfCharactor);
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderItem
		Set<OrderItem> orderItems = purchase.getOrderItem();
		orderItems.forEach(item -> order.add(item));
		
		//populate order with shippingAddress
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order		
		Customer customer = purchase.getCustomer();
		
		//Check if this is an existsing customer
		String theEmail = customer.getEmail();		
		Customer customerFromDB = customerRespository.findByEmail(theEmail); //return null if not found
		
		if (customerFromDB != null) { 
			if ((customerFromDB.getPhoneNumber() == null  || customerFromDB.getPhoneNumber().isEmpty()) && customer.getPhoneNumber().length() > 0) {
				customerFromDB.setPhoneNumber(customer.getPhoneNumber());
			}
			customer = customerFromDB;
		}
		
		customer.add(order);
		
		//save to the database
		customerRespository.save(customer);
		
		try {
			sendEmailService.sendEmailForInvoice(purchase, orderTrackingNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber(int numberOfCharactor) {
//		return UUID.randomUUID().toString();		
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
	}
	private  int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
		
		

}
