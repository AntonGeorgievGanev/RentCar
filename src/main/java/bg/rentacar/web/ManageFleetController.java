package bg.rentacar.web;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.service.car.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ManageFleetController {

    private final CarService carService;

    public ManageFleetController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("allCarsDTO")
    private AllCarsDTO allCarsDTO(){
        return new AllCarsDTO();
    }

    @GetMapping("/manage-fleet")
    public String manageFleet(Model model){
        model.addAttribute("allCarsDTO", carService.getAllCarsDTO());
        return "manage-fleet";
    }
}
