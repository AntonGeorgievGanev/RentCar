package bg.rentacar.service.image;

import bg.rentacar.model.dto.ImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ImageService {
    void initDefaultCarImageInDb();
}
