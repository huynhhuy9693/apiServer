package com.fpt.main.controller;





import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.main.dto.FavouriteResponseDto;
import com.fpt.main.dto.FavouritesDto;
import com.fpt.main.services.FavouritesService;

import javassist.NotFoundException;


@Controller
@RequestMapping("/api/favourites")
public class FavouritesController {
	@Autowired
	FavouritesService favouritesService;
	@GetMapping("/{email}")
	public ResponseEntity<List<FavouriteResponseDto>> getFavourites(@PathVariable String email){
		
		List<FavouriteResponseDto> list = favouritesService.getFavourites(email);
		
		return new ResponseEntity<List<FavouriteResponseDto>>(list, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> addFavourite(@RequestBody FavouritesDto dto) throws NotFoundException {
								
		boolean result = favouritesService.addFavourites(dto);		
		
		return new ResponseEntity<>(result ,HttpStatus.OK);
	}
		
	@PostMapping("/delete")
	public ResponseEntity<Boolean> removeFavourite(@RequestBody FavouritesDto dto) throws NotFoundException {
				
		boolean result = favouritesService.removeFavourites(dto);		
		
		
		return new ResponseEntity<>(result ,HttpStatus.OK);
	}

}
