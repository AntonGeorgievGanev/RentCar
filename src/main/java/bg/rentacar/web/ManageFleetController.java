package bg.rentacar.web;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.EditCarDTO;
import bg.rentacar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        if (!model.containsAttribute("editCarDTO")) {
            EditCarDTO editCarDTO = carService.getCarForEdit(id);
            model.addAttribute("editCarDTO", editCarDTO);
        }
        return "edit-car";
    }

    @PostMapping("/edit/{id}")
    public String updateCar(@PathVariable("id") Long id, @Valid EditCarDTO editCarDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editCarDTO", editCarDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.editCarDTO", bindingResult);
            return "redirect:/manage-fleet/edit/{id}";
        }
        carService.editCar(id, editCarDTO);
        return "redirect:/manage-fleet";
    }
}
