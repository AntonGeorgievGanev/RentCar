package bg.rentacar.web;

import bg.rentacar.model.dto.AllCarsDTO;
import bg.rentacar.model.dto.OrderDTO;
import bg.rentacar.service.car.CarService;
import bg.rentacar.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddOrderController {

    private final OrderService orderService;

    private final CarService carService;

    public AddOrderController(OrderService orderService, CarService carService) {
        this.orderService = orderService;
        this.carService = carService;
    }

    @ModelAttribute("orderDTO")
    private OrderDTO orderDTO(){
        return new OrderDTO();
    }

    @ModelAttribute("allCarsDTO")
    private AllCarsDTO allCarsDTO(){
        return new AllCarsDTO();
    }

    @GetMapping("/book-car")
    public String bookCar(Model model){
        model.addAttribute("allCarsDTO", carService.getAllCarsDTO());
        return "book-car";
    }

    @PostMapping("book-car")
    public String bookCar(@Valid OrderDTO orderDTO, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO())
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderDTO", bindingResult);

            return "redirect:/book-car";
        }

        orderService.registerOrder(orderDTO);

        return "redirect:/";
    }
}
