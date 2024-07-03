package bg.rentacar.web;

import bg.rentacar.model.dto.UserLoginDTO;
import bg.rentacar.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userLoginDTO")
    private UserLoginDTO userLoginDTO(){
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("badCredential", true);
        modelAndView.addObject("userLoginDTO", userLoginDTO());

        return modelAndView;
    }
}
