package com.fpt.main.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Restaurant;

@RepositoryRestResource
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	//@Query("SELECT * FROM Dishes u WHERE u.fullName = :name")
	//http://localhost:8080/api/restaurants/search/findByFullNameContaining?name=?
	public Page<Restaurant> findByFullNameContainingAndIsActiveTrue(@Param("name") String name, Pageable pageable);
	
//	http://localhost:8080/api/restaurants/search/findByDishesListId?id=1
	public Restaurant findByDishesListId(@Param("id")Long id);
	
	//	http://localhost:8080/api/restaurants/search/findTop10ByOrderByUpdatedAtDesc
	public List<Restaurant> findTop8ByOrderByUpdatedAtDesc();
	
	//	http://localhost:8080/api/restaurants/search/findTop10ByOrderByDishesListUpdatedAtDesc
	public List<Restaurant> findTop8ByOrderByDishesListUpdatedAtDesc();	
	
	//	http://localhost:8080/api/restaurants/search/findTop10ByOrderByRatingListUpdatedAtDesc
	public List<Restaurant> findTop8ByOrderByRatingListUpdatedAtDesc();
	
	//	http://localhost:8080/api/restaurants/search/findTop8ByOrderByRateCountDesc
	public List<Restaurant> findTop8ByOrderByRateCountDesc();
	
	//	http://localhost:8080/api/restaurants/search/findTop8ByOrderByRateTotalDesc
	public List<Restaurant> findTop8ByIsActiveTrueOrderByRateTotalDesc();
	
	public List<Restaurant> findByIsActiveTrue();
}
