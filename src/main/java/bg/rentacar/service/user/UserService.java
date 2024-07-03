package bg.rentacar.service.user;

import bg.rentacar.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void initAdmin();

    void initUserRole();
}
