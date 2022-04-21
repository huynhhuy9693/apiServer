package com.fpt.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.main.entity.Customer;


public interface CustomerRespository extends JpaRepository<Customer, Long>{

	@Query("SELECT customer FROM Customer customer WHERE customer.email = :theEmail")
	Customer findByEmail(@Param(value = "theEmail") String theEmail);
	
}
