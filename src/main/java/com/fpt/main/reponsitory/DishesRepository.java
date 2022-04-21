package com.fpt.main.reponsitory;


import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.Dishes;

@RepositoryRestResource
public interface DishesRepository extends JpaRepository<Dishes, Long> {
	
	//@Query("SELECT u FROM Dishes u WHERE u.restaurant.id = :res")
	//http://localhost:8080/api/disheses/search/findAllByRestaurantId?id=?
	public Page<Dishes> findAllByRestaurantId(@Param("id")Long id, Pageable pageable);
	
	//@Query("SELECT * FROM Dishes u WHERE u.fullName = :name")
	//http://localhost:8080/api/disheses/search/findByNameContaining?name=?
	public Page<Dishes> findByNameContainingAndIsActiveTrue(@Param("name") String name, Pageable pageable); 
	
	//http://localhost:8080/api/disheses/search/findAllByDishCategoryId?id=?
	public Page<Dishes> findAllByDishCategoryId(@Param("id")Long id, Pageable pageable);
	
	@Query("Select d.name From Dishes d Where d.id = :id")
	public String getNameDishById(@Param("id") Long id);
	
	@Query(value = "select\r\n"
			+ "                d.*\r\n"
			+ "            from\r\n"
			+ "				dishes d join \r\n"
			+ "                order_item o on d.id = o.dishes_id\r\n"
			+ "                order by o.created_at desc\r\n"
			+ "                limit 8", nativeQuery = true)
	// http://localhost:8080/api/disheses/search/getDishWithNewOrder
	public Set<Dishes> getDishWithNewOrder();
	
	
}
