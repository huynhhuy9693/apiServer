package com.fpt.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.main.dto.RatingDto;
import com.fpt.main.dto.RatingresultDto;
import com.fpt.main.entity.Restaurant;
import com.fpt.main.services.RatingService;

import javassist.NotFoundException;



@Controller
@RequestMapping("/api/rating")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/restaurant")
	public ResponseEntity<RatingresultDto> ratingForRestaurant(@RequestBody RatingDto ratingDto) throws NotFoundException {
		
		Restaurant restaurant = ratingService.ratingForRestaurant(ratingDto);
		
		RatingresultDto result = new RatingresultDto();
		result.setRestaurantId(restaurant.getId());
		result.setRateTotal(restaurant.getRateTotal());
		result.setRateCount(restaurant.getRateCount());
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public ResponseEntity getAllRatingForRestaurant() {
				
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
