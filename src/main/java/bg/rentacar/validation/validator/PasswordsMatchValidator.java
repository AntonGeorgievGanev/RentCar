package bg.rentacar.validation.validator;

import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.validation.annotation.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserRegisterDTO> {
    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {
        if (userRegisterDTO.getPassword() == null || userRegisterDTO.getConfirmPassword() == null){
            return false;
        }
        boolean isMatch = userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());

        if (!isMatch){
            String message = "Passwords do not match!";
            context.unwrap(HibernateConstraintValidatorContext.class)
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isMatch;
    }
}
