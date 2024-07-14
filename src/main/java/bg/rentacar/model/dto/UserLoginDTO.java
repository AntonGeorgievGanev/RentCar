package bg.rentacar.model.dto;

import bg.rentacar.constant.UserConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = UserConstants.USER_USERNAME_EMPTY)
    @Size(min = 3, max = 20, message = UserConstants.USER_USERNAME_LENGTH)
    private String username;

    @NotBlank(message = UserConstants.USER_PASSWORD_EMPTY)
    @Size(min = 6, message = UserConstants.USER_PASSWORD_LENGTH)
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
