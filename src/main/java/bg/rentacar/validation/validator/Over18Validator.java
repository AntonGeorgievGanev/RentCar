package bg.rentacar.validation.validator;

import bg.rentacar.validation.annotation.Over18;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Over18Validator implements ConstraintValidator<Over18, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 18;
    }
}
