package bg.rentacar.service.image.impl;

import bg.rentacar.model.entity.Image;
import bg.rentacar.repository.ImageRepository;
import bg.rentacar.service.image.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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

    @Override
    public String save(String uploadDirectory, MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDirectory);
        Path filePath = uploadPath.resolve(fileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "src/uploads/images/cars/" + fileName;
    }
}
