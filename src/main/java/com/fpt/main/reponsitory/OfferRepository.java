package com.fpt.main.reponsitory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Offer;

@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	//  http://localhost:8080/api/offers/search/findAllByExpiredDateTimeAfter
	@Query("SELECT f FROM Offer f WHERE f.expiredDate >= curdate()")
	List<Offer> findAllByExpiredDateTimeAfter();
	
	
	//  http://localhost:8080/api/offers/search/findAllByExpiredDateTimeAndRestaurantId?id=
	@Query("SELECT f FROM Offer f WHERE f.expiredDate >= curdate() AND f.restaurant.id = :id")
	List<Offer> findAllByExpiredDateTimeAndRestaurantId(@Param("id")Long id);
	
}
