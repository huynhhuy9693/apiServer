package com.fpt.main.dto;


import lombok.Data;

@Data
public class RatingresultDto {
	private Long restaurantId;
	private int rateCount;
	private float rateTotal;
}
