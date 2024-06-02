package com.karolkusper.KINEMA.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageUploadController {
    private static final String UPLOAD_DIR = "C:/Users/karol/Documents/studia_pk/semestr_6/ztpai/PROJECT/KINEMA/frontend/KINEMA_FRONTEND/public";

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            if (image.isEmpty()) {
                return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
            }

            // Generate a unique filename based on the current timestamp
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR, fileName);

            // Ensure the directory exists
            Files.createDirectories(uploadPath.getParent());

            // Save the file
            Files.write(uploadPath, image.getBytes());

            // Construct the URL to access the image
            String imageUrl = "http://localhost:5173/" + fileName;

            return new ResponseEntity<>(new ImageUploadResponse(imageUrl), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Inner class to represent the response
    public static class ImageUploadResponse {
        private String url;

        public ImageUploadResponse(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
