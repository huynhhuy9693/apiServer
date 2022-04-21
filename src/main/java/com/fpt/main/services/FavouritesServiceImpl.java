package com.fpt.main.services;






import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.fpt.main.entity.Customer;
import com.fpt.main.entity.Restaurant;
import com.fpt.main.reponsitory.CustomerRespository;
import com.fpt.main.reponsitory.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fpt.main.dto.FavouriteResponseDto;
import com.fpt.main.dto.FavouritesDto;
import javassist.NotFoundException;





@Service
public class FavouritesServiceImpl implements FavouritesService{

	@Autowired
	CustomerRespository customerRespository;

	@Autowired
	RestaurantRepository restaurantRepository;
	@Override
	public boolean addFavourites(FavouritesDto dto) throws NotFoundException {
		Customer customer = customerRespository.findByEmail(dto.getCustomerEmail().replace("\"", ""));

		if (customer == null) {
			customer = new Customer();
			customer.setEmail(dto.getCustomerEmail());
			customer.setFullName(dto.getCustomerEmail().split("@")[0]);
		}

		
		Restaurant restaurant = null;
		
		Optional<Restaurant> result = restaurantRepository.findById(dto.getRestaurantId());
		if (result.isPresent()) {
			restaurant = result.get();
		}else {
			return false;
		}

		customer.addRestaurantFavou(restaurant);
		customerRespository.save(customer);
		
		return true;
	}

	@Override
	public boolean removeFavourites(FavouritesDto dto) {
		Customer customer = customerRespository.findByEmail(dto.getCustomerEmail().replace("\"", ""));

		if (customer == null) {
			return false;
		}
		
		Restaurant restaurant = null;		
		Optional<Restaurant> result = restaurantRepository.findById(dto.getRestaurantId());
		if (result.isPresent()) {
			restaurant = result.get();
		}else {
			return false;
		}
		
		customer.removeRestaurantFavou(restaurant);
		customerRespository.save(customer);
		
		return true;
	}

	@Override
	public List<FavouriteResponseDto> getFavourites(String email) {
List<FavouriteResponseDto> result = new ArrayList<FavouriteResponseDto>();
		
		Set<Restaurant> list = new HashSet<Restaurant>();
		
		Customer customer = customerRespository.findByEmail(email.replace("\"", ""));
		
		if(customer == null) {
			return result;
		}
		list = customer.getRestaurantFavou();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Restaurant restaurant = (Restaurant) iterator.next();			
				
			if (restaurant.isActive()) {
				
				FavouriteResponseDto dto = new FavouriteResponseDto();
				dto.setId(restaurant.getId());
				dto.setFullName(restaurant.getFullName());
				dto.setEmail(restaurant.getEmail());
				dto.setPhoneNumber(restaurant.getPhoneNumber());
				dto.setImageURL(restaurant.getImageURL());
				dto.setRateCount(restaurant.getRateCount());
				dto.setRateTotal(restaurant.getRateTotal());
				dto.setAddress(restaurant.getAddress().getFullAddress());
				dto.setActive(restaurant.isActive());
				
				result.add(dto);
			}						
		}
	
		return result;
		
	}
}

	

	

	


