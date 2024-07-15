package bg.rentacar.service.user;

import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.model.dto.UserLoginDTO;
import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.model.entity.User;

import java.util.Optional;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void initAdmin();

    void initUserRole();

    boolean validateLogin(UserLoginDTO userLoginDTO);

    User getUserByName(String name);

    AllUsersInfoDTO getAllUserInfo();
}
