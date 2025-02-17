package bg.rentacar.validation.annotation;


import bg.rentacar.validation.validator.FileImageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileImageValidator.class)
public @interface FileImage {
    String message() default "Invalid image format!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
