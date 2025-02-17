package bg.rentacar.web;

import bg.rentacar.model.dto.UserLoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        logger.info("Login error detected");
        model.addAttribute("badCredentials", true);
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }
}
