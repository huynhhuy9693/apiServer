package com.fpt.main.services;

import java.util.List;

import com.fpt.main.dto.FavouriteResponseDto;
import com.fpt.main.dto.FavouritesDto;

import javassist.NotFoundException;

public interface FavouritesService {

	boolean addFavourites(FavouritesDto dto) throws NotFoundException;
	boolean removeFavourites(FavouritesDto dto);
	List<FavouriteResponseDto> getFavourites(String email);
}
