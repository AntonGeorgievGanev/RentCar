package bg.rentacar.validation.validator;

import bg.rentacar.validation.annotation.FileImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FileImageValidator implements ConstraintValidator<FileImage, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true; // Empty files are considered valid
        }

        try {
            return ImageIO.read(file.getInputStream()) != null;
        } catch (IOException e) {
            return false; // Invalid image
        }
    }
}
