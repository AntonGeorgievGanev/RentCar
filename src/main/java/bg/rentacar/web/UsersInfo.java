package bg.rentacar.web;

import bg.rentacar.model.dto.AllUsersInfoDTO;
import bg.rentacar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @ModelAttribute("allEmployeesDTO")
    private AllUsersInfoDTO allEmployeesDTO(){
        return new AllUsersInfoDTO();
    }

    @GetMapping("/users-info")
    public String users(Model model){
        model.addAttribute("allUsersDTO", userService.getAllUserInfo());
        model.addAttribute("allEmployeesDTO", userService.getAllEmployeesInfo());
        return "users-info";
    }

    @PostMapping("/users-info/promote/{id}")
    public String promoteToEmployee(@PathVariable Long id){
       userService.promoteUserToEmployee(id);
        return "redirect:/users-info";
    }

    @PostMapping("/users-info/demote/{id}")
    public String demoteToEmployee(@PathVariable Long id){
        userService.demoteEmployee(id);
        return "redirect:/users-info";
    }
}
