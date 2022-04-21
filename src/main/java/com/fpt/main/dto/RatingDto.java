package com.fpt.main.dto;

import lombok.Data;

@Data
public class RatingDto {
	private float rate;
	private String note;
	private String customerEmail;
	private Long restaurantId;
	
}
