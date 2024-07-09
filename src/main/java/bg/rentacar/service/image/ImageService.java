package bg.rentacar.service.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void initDefaultCarImageInDb();
    String save(String uploadDirectory, MultipartFile image) throws IOException;
}
