package bg.rentacar.model.dto;

import bg.rentacar.validation.annotation.Over18;
import bg.rentacar.validation.annotation.UniqueEmail;
import bg.rentacar.validation.annotation.UniquePhoneNumber;
import bg.rentacar.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = "First name must not be empty!")
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Last name must not be empty!")
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters!")
    private String lastName;

    @NotBlank(message = "Username name must not be empty!")
    @UniqueUsername
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters!")
    private String username;

    @NotBlank(message = "Email must not be empty!")
    @Email
    @UniqueEmail
    private String email;

    @NotBlank(message = "Phone number must not be empty!")
    @UniquePhoneNumber
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @NotNull(message = "Your age is required!")
    @Positive
    @Over18
    private int age;

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @NotBlank(message = "Confirm password must not be empty!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
