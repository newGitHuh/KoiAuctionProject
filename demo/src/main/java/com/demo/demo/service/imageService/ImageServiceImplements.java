package com.demo.demo.service.imageService;

import com.demo.demo.model.Image;
import com.demo.demo.repository.ImageRepo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ImageServiceImplements implements ImageService{
    private static final Logger log = LoggerFactory.getLogger(ImageServiceImplements.class);
    private ImageRepo imageRepo;

    @Override
    public void uploadImage(List<MultipartFile> file) {
    try{
        for(MultipartFile fileItem : file){
            Image img = Image.builder()

                    .imageName(fileItem.getName())
                    .imageData(fileItem.getBytes())
                    .build();

            imageRepo.save(img);
        }

    } catch (Exception e){
        log.error(e.getMessage());
    }

    }

    @Override
    public byte[] getImage(Long id) {
        Optional<Image> imageData = imageRepo.findById(id);
        return imageData.map(Image::getImageData).orElse(null);

    }


}
