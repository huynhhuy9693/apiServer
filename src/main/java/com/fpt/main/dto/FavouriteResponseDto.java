package com.fpt.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteResponseDto {

	public Long id ;
	public String fullName;
	public String email;
	public float rateTotal;
	public int rateCount;
	public String phoneNumber;
	public String imageURL;
	public boolean isActive;
	public String address;
	

}
