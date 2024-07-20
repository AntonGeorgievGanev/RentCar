package bg.rentacar.service;

import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.model.dto.UserLoginDTO;
import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.model.entity.User;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void initAdmin();

    void initUserRole();

    boolean validateLogin(UserLoginDTO userLoginDTO);

    User getUserByName(String name);

    AllUsersInfoDTO getAllUserInfo();
}
