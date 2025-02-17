package bg.rentacar.validation.annotation;

import bg.rentacar.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email is already taken!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
