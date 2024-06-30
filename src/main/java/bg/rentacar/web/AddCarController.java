package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddCarController {

    @GetMapping("add-car")
    public String addCar(){
        return "add-car";
    }
}
