package com.fpt.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.main.dto.Purchase;
import com.fpt.main.dto.PurchaseResponse;
import com.fpt.main.services.CheckoutService;


@Controller
@RequestMapping("/api/checkout")
@RestController
public class CheckoutController {
	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping("/purchase")
	public  ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) {
		
		System.out.println("purchase: " + purchase.toString());
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		System.out.println("=================CHECK OUT CONTROLLER===================");
		return new ResponseEntity<PurchaseResponse>(purchaseResponse, HttpStatus.OK);
	}
	
	
	
}
