package bg.rentacar.web;

import bg.rentacar.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarDetailsController {

    private final CarService carService;

    public CarDetailsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car-details/{id}")
    public String carDetails(@PathVariable Long id, Model model){
        model.addAttribute("carDTO", carService.getCarById(id));
        return "car-details";
    }
}
