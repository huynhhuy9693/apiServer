package com.fpt.main.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dishes extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "quantity", columnDefinition = "int default 0")
	private int quantity;
	
	@Column(name = "rate_total", columnDefinition = "float default 0.0")
	private float rateTotal;
	
	@Column(name = "rate", columnDefinition = "float default 0.0")
	private float rate;

	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name ="note")
	private String note;

	@ManyToOne()
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@Column(name = "img_url")
	private String imageURL;
	
	@Column(name = "is_active", columnDefinition = "Bit(1) default true")
	private boolean isActive;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "offer_id")
	private Offer offer ;
	
	@ManyToOne
	@JoinColumn(name = "dish_category_id")
	private DishCategories dishCategory;

	
}
