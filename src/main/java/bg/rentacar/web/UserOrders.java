package bg.rentacar.web;

import bg.rentacar.model.dto.AllUserOrdersDTO;
import bg.rentacar.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class UserOrders {

    private final OrderService orderService;

    public UserOrders(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("allUserOrders")
    private AllUserOrdersDTO allUserOrdersDTO(){
        return new AllUserOrdersDTO();
    }

    @GetMapping("/my-orders")
    public String userOrders(Model model, Principal principal){
        model.addAttribute("allUserOrders", orderService.getAllUserOrders(principal));
        return "user-orders";
    }
}
