package ru.avangard.website.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class ImageUploadController {

    @Value("${upload.dir:uploads}")
    private String uploadDir; // по умолчанию uploads/

    @PostMapping("/api/upload/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Файл пуст");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + extension;

        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        // Возвращаем URL, который можно сохранить в Service
        String imageUrl = "/images/" + fileName;
        return ResponseEntity.ok(imageUrl);
    }
}
