package bg.rentacar.validation.annotation;

import bg.rentacar.validation.validator.UniquePhoneNumberValidator;
import bg.rentacar.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniquePhoneNumberValidator.class)
public @interface UniquePhoneNumber {

    String message() default "Phone number is already register!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
