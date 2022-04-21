package com.fpt.main.services;

import com.fpt.main.dto.RatingDto;
import com.fpt.main.entity.Restaurant;

import javassist.NotFoundException;

public interface RatingService {
	
	Restaurant ratingForRestaurant(RatingDto item) throws NotFoundException;
	
	void getAllRatingForRestaurant();
}
