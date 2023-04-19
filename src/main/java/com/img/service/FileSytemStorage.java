package com.img.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.img.entities.FileData;
import com.img.repo.FileDataRepository;

@Service
public class FileSytemStorage {

	@Autowired
	private FileDataRepository repo;

	private final String FOLDER_PATH = "C:\\Users\\JBK\\BackUp\\OneDrive\\Desktop\\db\\";

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();

		FileData fileData = repo.save(FileData.builder().name(file.getOriginalFilename()).type(file.getContentType())
				.filePath(filePath).build());

		file.transferTo(new File(filePath));

		if (fileData != null) {
			return "file uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileData> fileData = repo.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}
	
	public List<FileData> viewAll(){
		List<FileData> findAll = repo.findAll();
		return findAll;
	}
}
