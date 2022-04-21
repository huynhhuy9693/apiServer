package com.fpt.main.services;

import com.fpt.main.dto.Purchase;
import com.fpt.main.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
