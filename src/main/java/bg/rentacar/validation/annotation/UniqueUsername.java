package bg.rentacar.validation.annotation;

import bg.rentacar.validation.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {

    String message() default "Username is already taken!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
