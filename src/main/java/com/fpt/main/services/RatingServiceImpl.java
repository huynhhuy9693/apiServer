package com.fpt.main.services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.main.dto.RatingDto;
import com.fpt.main.entity.Customer;
import com.fpt.main.entity.Rating;
import com.fpt.main.entity.Restaurant;
import com.fpt.main.reponsitory.CustomerRespository;
import com.fpt.main.reponsitory.RatingRepository;
import com.fpt.main.reponsitory.RestaurantRepository;

import javassist.NotFoundException;



@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private CustomerRespository customerRespository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Transactional
	public Restaurant ratingForRestaurant(RatingDto rateItem) throws NotFoundException {
		
		Customer customer = customerRespository.findByEmail(rateItem.getCustomerEmail());
		
		if (customer == null) {
			throw new NotFoundException("Customer not found");
		}
		
		Restaurant restaurant = restaurantRepository.findById(rateItem.getRestaurantId())
													.orElseThrow(() -> new RuntimeException("Restaurant not found !"));
		
		Rating rating = new Rating();
		rating.setCustomer(customer);
		rating.setRate(rateItem.getRate());
		rating.setNote(rateItem.getNote());
		rating.setRestaurant(restaurant);
		//save to DB
		ratingRepository.save(rating);
		
		String strResult = ratingRepository.getRatingOfRest(restaurant.getId());			
		String[] arrRstl = strResult.split(",");	
		
		//Recalculate rate value and rate count for Restaurant
		restaurant.setRateTotal(Float.parseFloat(arrRstl[0]));
		restaurant.setRateCount(Integer.parseInt(arrRstl[1]));		
		restaurantRepository.save(restaurant);		
		
		//return Restaurant with new rate to client
		return restaurant;
		
	}


	@Override
	public void getAllRatingForRestaurant() {
		
		
	}

}
