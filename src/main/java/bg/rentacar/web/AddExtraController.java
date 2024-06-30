package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddExtraController {

    @GetMapping("/add-extra")
    public String addExtra(){
        return "add-extra";
    }
}
