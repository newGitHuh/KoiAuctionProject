package com.demo.demo.controller.imageController;

import com.demo.demo.service.imageService.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/image")
@RestController
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;


    @PostMapping("/saveImg")
    public String saveImgToDb(@RequestParam("File") List<MultipartFile> file) throws IOException {

        imageService.uploadImage(file);

        return "complete";
    }

    @GetMapping(value = "/loadImgFromDb", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> loadImgFromDb(@RequestParam("id") Long id) {

        return new ResponseEntity<>(imageService.getImage(id), HttpStatus.OK);
    }
}
