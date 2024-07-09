package bg.rentacar.web;

import bg.rentacar.model.dto.CarsByCategoryDTO;
import bg.rentacar.service.car.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FleetController {

    private final CarService carService;

    public FleetController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("carsByCategoryDTO")
    private CarsByCategoryDTO carsByCategoryDTO(){
        return new CarsByCategoryDTO();
    }

    @GetMapping("/fleet")
    public String fleet(Model model){
        CarsByCategoryDTO carsByCategoryDTO = carService.getCarsByCategory();
        model.addAttribute("carsByCategoryDTO", carsByCategoryDTO);
        return "fleet";
    }
}
