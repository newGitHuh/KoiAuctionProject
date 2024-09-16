package com.demo.demo.service.imageService;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {


    void uploadImage(List<MultipartFile> file);

    byte[] getImage(Long id);
}
