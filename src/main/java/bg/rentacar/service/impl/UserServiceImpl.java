package bg.rentacar.service.impl;

import bg.rentacar.exception.ObjectNotFound;
import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.model.dto.UserInfoDTO;
import bg.rentacar.model.dto.UserLoginDTO;
import bg.rentacar.model.dto.UserRegisterDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.model.entity.UserRole;
import bg.rentacar.model.enums.Role;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.repository.UserRoleRepository;
import bg.rentacar.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder encoder;

//    @Value("${admin.password}")
//    private String adminPassword;
//
//    @Value("${admin.name}")
//    private String adminName;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = mapper.map(userRegisterDTO, User.class);
        user.setPassword(encoder.encode(userRegisterDTO.getPassword()));
        UserRole role = userRoleRepository.findByRole(Role.USER)
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public void initAdmin() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setFirstName("Anton");
            user.setLastName("Ganev");
            user.setUsername("admin");
            user.setEmail("admin@rentacar.com");
            user.setPhoneNumber("1234567890");
            user.setAge(37);
            user.setPassword(encoder.encode("123456"));

            UserRole role = userRoleRepository.findByRole(Role.ADMIN)
                    .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
            role.setRole(Role.ADMIN);

            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void initUserRole() {
        if (userRoleRepository.count() == 0) {
            for (int i = 0; i < Role.values().length; i++) {
                UserRole userRole = new UserRole();
                Role role = Role.values()[i];
                userRole.setRole(role);
                userRoleRepository.save(userRole);
            }
        }
    }

    @Override
    public boolean validateLogin(UserLoginDTO userLoginDTO) {
        Optional<User> userOpt = userRepository.findByUsername(userLoginDTO.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return encoder.matches(userLoginDTO.getPassword(), user.getPassword());
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new ObjectNotFound("This object cannot be found."));
    }

    @Override
    public AllUsersInfoDTO getAllUserInfo() {
        List<UserInfoDTO> usersDTO = userRepository.findAll().stream().filter(user -> user.getRoles().size() == 1 &&
                user.getRoles().getFirst().getRole().name().equals(Role.USER.name()))
                .map(user -> mapper.map(user, UserInfoDTO.class)).toList();
        return new AllUsersInfoDTO(usersDTO);
    }
}
