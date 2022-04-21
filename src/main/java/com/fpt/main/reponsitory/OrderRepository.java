package com.fpt.main.reponsitory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Order;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//	http://localhost:8080/api/orders/search/findByCustomerEmailOrderByCreatedAtDesc?email=khoaimiday@gmail.com
	Page<Order> findByCustomerEmailOrderByCreatedAtDesc(@Param("email") String email, Pageable pageable);
	
	Optional<Order> findByOrderTrackingNumber(@Param("trackNumber") String trackNumber);
}
