package com.fpt.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.OrderItem;

@RepositoryRestResource
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query("Select i From OrderItem i Where i.order.orderTrackingNumber = :trackNumber")
	List<OrderItem> findAllByOrderTrackingNumber(@Param("trackNumber") String trackNumber);
}

