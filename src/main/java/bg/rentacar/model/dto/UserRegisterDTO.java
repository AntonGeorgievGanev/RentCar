package bg.rentacar.model.dto;

import bg.rentacar.constant.UserConstants;
import bg.rentacar.validation.annotation.Over18;
import bg.rentacar.validation.annotation.UniqueEmail;
import bg.rentacar.validation.annotation.UniquePhoneNumber;
import bg.rentacar.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = UserConstants.USER_FIRST_NAME_EMPTY)
    @Size(min = 3, max = 20, message = UserConstants.USER_FIRST_NAME_LENGTH)
    private String firstName;

    @NotBlank(message = UserConstants.USER_LAST_NAME_EMPTY)
    @Size(min = 3, max = 20, message = UserConstants.USER_LAST_NAME_LENGTH)
    private String lastName;

    @NotBlank(message = UserConstants.USER_USERNAME_EMPTY)
    @UniqueUsername
    @Size(min = 3, max = 20, message = UserConstants.USER_USERNAME_LENGTH)
    private String username;

    @NotBlank(message = UserConstants.USER_EMAIL_EMPTY)
    @Email
    @UniqueEmail
    private String email;

    @NotBlank(message = UserConstants.USER_PHONE_NUMBER_EMPTY)
    @UniquePhoneNumber
    @Size(min = 10, max = 13, message = UserConstants.USER_PHONE_NUMBER_LENGTH)
    private String phoneNumber;

    @NotNull(message = UserConstants.USER_AGE_EMPTY)
    @Positive(message = UserConstants.USER_AGE_NEGATIVE)
    @Over18
    private Integer age;

    @NotBlank(message = UserConstants.USER_PASSWORD_EMPTY)
    @Size(min = 6, message = UserConstants.USER_PASSWORD_LENGTH)
    private String password;

    @NotBlank(message = UserConstants.USER_CONFIRM_PASSWORD_EMPTY)
    @Size(min = 6, message = UserConstants.USER_CONFIRM_PASSWORD_LENGTH)
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
