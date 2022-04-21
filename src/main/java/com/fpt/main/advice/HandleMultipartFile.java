package com.fpt.main.advice;



import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class HandleMultipartFile {

	int maximumSizeMB = 1500000;

	public String SaveAvatarImage(MultipartFile multipartFile, Long userId) throws IOException {

		if (multipartFile.getSize() > maximumSizeMB) {
			throw new IOException("Avatar Image size is too big. Maximum size is 1,5 MB");
		}

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String uploadDir = "/avatar/" + userId;

		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);			
			System.out.println(filePath.toFile().getAbsolutePath() + "=========================");
			
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			Files.delete(uploadPath);
			throw new IOException("Could not save upload avarta: " + fileName);
		}
		return fileName;
	}
}
