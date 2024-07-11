package bg.rentacar.web;

import bg.rentacar.model.dto.AllOrdersByStatus;
import bg.rentacar.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ManageOrdersController {

    private final OrderService orderService;

    public ManageOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("allOrdersByStatus")
    private AllOrdersByStatus allOrdersByStatus(){
        return new AllOrdersByStatus();
    }

    @GetMapping("/manage-orders")
    public String manageOrders(Model model) {
        AllOrdersByStatus allOrdersByStatus = orderService.getAllOrdersByStatus();
        model.addAttribute("allOrdersByStatus", allOrdersByStatus);
        return "manage-orders";
    }
}
