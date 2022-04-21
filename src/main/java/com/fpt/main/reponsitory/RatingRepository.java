package com.fpt.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Rating;

@RepositoryRestResource
public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	Long countByRestaurantId(@Param(value = "id") Long id);
		
	@Query(value = "SELECT ROUND( AVG(rt.rate), 1) AS ratingValue 	   ,"
			+ "       	   COUNT(rt.id) AS ratingCount, 			\r\n"
			+ "            SUM(rt.rate) AS ratingSum 				\r\n"
			+ "FROM ratings rt 										\r\n"
			+ "WHERE rt.restaurant_id = :id", nativeQuery = true)
	String getRatingOfRest(@Param(value = "id") Long id);
}
