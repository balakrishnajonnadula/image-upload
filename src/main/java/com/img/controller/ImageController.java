package com.img.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.img.entities.FileData;
import com.img.service.FileSytemStorage;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000/")
public class ImageController {
	
	@Autowired
	private FileSytemStorage fss;
	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = fss.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData=fss.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	
	@GetMapping("/fileSystem/viewall")
	public ResponseEntity<?> viewAll(){
		List<FileData> viewAll = fss.viewAll();
		return ResponseEntity.ok().body(viewAll);
	}

}
