package bg.rentacar.init;

import bg.rentacar.service.image.ImageService;
import bg.rentacar.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;

    private final ImageService imageService;

    public AppInit(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUserRole();
        userService.initAdmin();
        imageService.initDefaultCarImageInDb();
    }
}
