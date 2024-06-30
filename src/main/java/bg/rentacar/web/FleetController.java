package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FleetController {

    @GetMapping("/fleet")
    public String fleet(){
        return "fleet";
    }
}
