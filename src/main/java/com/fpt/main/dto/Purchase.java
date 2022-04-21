package com.fpt.main.dto;



import java.util.Set;

import com.fpt.main.entity.Address;
import com.fpt.main.entity.Customer;
import com.fpt.main.entity.Order;
import com.fpt.main.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	private Customer customer;
	private Address shippingAddress;
	private Order order;
	private Set<OrderItem> orderItem;
	
	
}
