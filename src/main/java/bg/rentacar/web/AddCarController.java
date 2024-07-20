package bg.rentacar.web;

import bg.rentacar.model.dto.CarDTO;
import bg.rentacar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class AddCarController {

    private final CarService carService;


    public AddCarController(CarService carService) {
        this.carService = carService;
    }

    @ModelAttribute("addCarDTO")
    private CarDTO addCarDTO() {
        return new CarDTO();
    }

    @GetMapping("add-car")
    public String addCar() {
        return "add-car";
    }

    @PostMapping("/add-car")
    public String addCar(@Valid CarDTO addCarDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         @RequestParam("carImage") MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addCarDTO", addCarDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCarDTO", bindingResult);

            return "redirect:/add-car";
        }

        carService.addCarWithImage(addCarDTO, file);
        return "redirect:/fleet";
    }
}
