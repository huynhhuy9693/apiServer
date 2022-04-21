package com.fpt.main.controller;






import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.main.advice.HandleMultipartFile;
import com.fpt.main.dto.FavouriteResponseDto;
import com.fpt.main.dto.FavouritesDto;
import com.fpt.main.entity.Restaurant;
import com.fpt.main.reponsitory.DishesRepository;
import com.fpt.main.services.FavouritesService;
import com.fpt.main.services.FavouritesServiceImpl;
import com.fpt.main.services.ReportService;






@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	DishesRepository dishesRepository;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private FavouritesServiceImpl favouritesServiceImpl;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	public String userAccess() {
		return "User Content.";
	}
	
	@PostMapping("/user")
	public String userAccess1() {
		return "User Content.";
	}
	
	@GetMapping("/favourite/{email}")
	public ResponseEntity<List<FavouriteResponseDto>> testFavourite(@PathVariable String email) {
		List<FavouriteResponseDto> result = favouritesServiceImpl.getFavourites(email);
		
		return new ResponseEntity<List<FavouriteResponseDto>>(result, HttpStatus.OK);
	}
	
		
	@PostMapping("/favourite")
	public String testFavourite(@RequestBody FavouritesDto dto) {
//		 request.getRequestDispatcher("/api/favourites").forward(request, response);
		return "success";
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@PostMapping("/profile")
	public String testUploadImage(@RequestParam(name="fileImage",required = false) MultipartFile multipartFile) throws IOException {
		HandleMultipartFile handleMultipartFile = new HandleMultipartFile();
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String fileName = handleMultipartFile.SaveAvatarImage(multipartFile, 123L);
			return fileName;
		}	
		return null;
	}
	

}
