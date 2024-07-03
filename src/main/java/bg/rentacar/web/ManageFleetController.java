package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageFleetController {

    @GetMapping("/manage-fleet")
    public String manageFleet(){
        return "manage-fleet";
    }
}
