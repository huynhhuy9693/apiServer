package com.fpt.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ratings")
public class Rating extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rate")
	private float rate;
	
	@Column(name = "note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
}

