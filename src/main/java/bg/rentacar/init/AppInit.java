package bg.rentacar.init;

import bg.rentacar.repository.UserRoleRepository;
import bg.rentacar.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;

    public AppInit(UserService userService, UserRoleRepository userRoleRepository) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUserRole();
        userService.initAdmin();
    }
}
