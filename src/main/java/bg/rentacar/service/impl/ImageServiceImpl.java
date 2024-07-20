package bg.rentacar.service.impl;

import bg.rentacar.model.entity.Image;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.ImageService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void initDefaultCarImageInDb() {
        if (imageRepository.count() == 0) {
            String uploadDirectory = "/images/";
            String fileName = "default_car_image78456321.jpg";

            Path uploadPath = Path.of(uploadDirectory);
            Path filePath = uploadPath.resolve(fileName);

            String location = filePath.toString();

            Image image = new Image();
            image.setName(fileName);
            image.setLocation(location);
            imageRepository.save(image);
        }
    }
}


