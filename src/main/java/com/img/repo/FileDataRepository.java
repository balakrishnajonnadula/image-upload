package com.img.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.img.entities.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Integer> {
	 Optional<FileData> findByName(String fileName);
}
