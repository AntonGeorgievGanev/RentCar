package bg.rentacar.service.user.impl;

import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.model.entity.UserRole;
import bg.rentacar.model.enums.Role;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.repository.UserRoleRepository;
import bg.rentacar.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper mapper;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = mapper.map(userRegisterDTO, User.class);
        userRepository.save(user);
    }

    @Override
    public void initAdmin() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setFirstName("Anton");
            user.setLastName("Ganev");
            user.setUsername("admin");
            user.setEmail("a.g.ganev@abv.bg");
            user.setPhoneNumber("1234567890");
            user.setAge(37);
            user.setPassword(encoder.encode("admin123"));

            UserRole role = userRoleRepository.findByRole(Role.ADMIN).get();
            role.setRole(Role.ADMIN);

            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void initUserRole() {
        if (userRoleRepository.count() == 0){
            for (int i = 0; i < Role.values().length  ; i++) {
                UserRole userRole = new UserRole();
                Role role = Role.values()[i];
                userRole.setRole(role);
                userRoleRepository.save(userRole);
            }
        }
    }
}
