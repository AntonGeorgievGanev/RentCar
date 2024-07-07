package bg.rentacar.web;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.AllExtrasDTO;
import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.service.car.CarService;
import bg.rentacar.service.extra.ExtraService;
import bg.rentacar.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class AddOrderController {

    private final OrderService orderService;

    private final CarService carService;

    private final ExtraService extraService;

    public AddOrderController(OrderService orderService, CarService carService, ExtraService extraService) {
        this.orderService = orderService;
        this.carService = carService;
        this.extraService = extraService;
    }

    @ModelAttribute("orderDTO")
    private OrderDTO orderDTO(){
        return new OrderDTO();
    }

    @ModelAttribute("allCarsDTO")
    private AllCarsDTO allCarsDTO(){
        return new AllCarsDTO();
    }

    @ModelAttribute("allExtrasDTO")
    private AllExtrasDTO allExtrasDTO(){
        return new AllExtrasDTO();
    }

    @GetMapping("/book-car")
    public String bookCar(Model model){
        model.addAttribute("allCarsDTO", carService.getAllCarsDTO());
        model.addAttribute("allExtrasDTO", extraService.getAllExtras());
        return "book-car";
    }

    @PostMapping("book-car")
    public String bookCar(@Valid OrderDTO orderDTO, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Principal principal){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO())
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderDTO", bindingResult);

            return "redirect:/book-car";
        }

        orderService.registerOrder(orderDTO, principal.getName());

        return "redirect:/";
    }
}
