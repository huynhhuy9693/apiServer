package com.fpt.main.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dish_category")
@Getter
@Setter
public class DishCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dishCategory")
	private Set<Dishes> dishList = new HashSet<>();
	
	public void addDishes(Dishes item) {
		if (item != null) {
			if (dishList == null) {
				dishList = new HashSet<>();
			}
		}
		dishList.add(item);
		item.setDishCategory(this);
	}
}
