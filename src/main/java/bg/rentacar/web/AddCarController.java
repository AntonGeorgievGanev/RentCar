package bg.rentacar.web;

import bg.rentacar.model.dto.AddCarDTO;
import bg.rentacar.service.car.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddCarController {

    private final CarService carService;

    public AddCarController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("addCarDTO")
    private AddCarDTO addCarDTO(){
        return new AddCarDTO();
    }

    @GetMapping("add-car")
    public String addCar(){
        return "add-car";
    }

    @PostMapping("/add-car")
    public String addCar(@Valid AddCarDTO addCarDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addCarDTO", addCarDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCarDTO", bindingResult);

            return "redirect:/add-car";
        }

        carService.addNewCar(addCarDTO);
        return "redirect:/fleet";
    }
}
