package bg.rentacar.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
//    @NotBlank(message = "Username cannot be empty!")
//    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

//    @NotBlank(message = "Password must not be empty!")
//    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
