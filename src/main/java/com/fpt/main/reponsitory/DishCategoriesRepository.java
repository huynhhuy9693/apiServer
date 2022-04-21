package com.fpt.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fpt.main.entity.DishCategories;

@RepositoryRestResource
public interface DishCategoriesRepository extends JpaRepository<DishCategories, Long> {
}
