package bg.rentacar.web;

import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UsersInfo {
    private final UserService userService;

    public UsersInfo(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("allUsersDTO")
    private AllUsersInfoDTO allUsersInfoDTO(){
        return new AllUsersInfoDTO();
    }

    @GetMapping("/users-info")
    public String users(Model model){
        model.addAttribute("allUsersDTO", userService.getAllUserInfo());
        return "users-info";
    }
}
