package com.fpt.main.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Address;

@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long> {


	//  http:localhost:8080/api/addresses/search/findByRestaurant?id=?
	@Query("SELECT address FROM Address address WHERE address.restaurant.id = :id ")
	public Address findByRestaurant(@Param("id") Long id);
	
	
	//  http://localhost:8080/api/addresses/search/findAllByCustomer?id=?
//	@Query("SELECT address FROM Address address WHERE address.user.id = :id")
//	public Page<Address> findAllByUser(@RequestParam("id") Long id, Pageable pageable);
	
//	public Page<Address> findAllByCustomerId(@Param("id") Long id, Pageable pageable);
	
}
