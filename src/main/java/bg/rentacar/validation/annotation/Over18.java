package bg.rentacar.validation.annotation;

import bg.rentacar.validation.validator.Over18Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = Over18Validator.class)
public @interface Over18 {
    String message() default "You must be at least 18 years old!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
