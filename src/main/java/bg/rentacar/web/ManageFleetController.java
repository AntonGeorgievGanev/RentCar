package bg.rentacar.web;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.EditCarDTO;
import bg.rentacar.service.car.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage-fleet")
public class ManageFleetController {

    private final CarService carService;

    public ManageFleetController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("allCarsDTO")
    private AllCarsDTO allCarsDTO(){
        return new AllCarsDTO();
    }

    @ModelAttribute("editCarDTO")
    private EditCarDTO editCarDTO(){
        return new EditCarDTO();
    }

    @GetMapping
    public String manageFleet(Model model){
        model.addAttribute("allCarsDTO", carService.getAllAvailableCarsDTO());
        return "manage-fleet";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return "redirect:/manage-fleet";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, Model model){
        model.addAttribute("editCarDTO", carService.getCarForEdit(id));
        return "edit-car";
    }

    @PostMapping("/edit/{id}")
    public String updateCar(@PathVariable("id") Long id, EditCarDTO editCarDTO) {
        carService.editCar(id, editCarDTO);
        return "redirect:/manage-fleet";
    }
}
