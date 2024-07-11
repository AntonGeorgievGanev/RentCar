package bg.rentacar.web;

import bg.rentacar.model.dto.AllOrdersByStatus;
import bg.rentacar.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/manage-orders/approve/{id}")
    public String approveOrder(@PathVariable Long id){
        orderService.approveOrder(id);
        return "redirect:/manage-orders";
    }

    @PostMapping("/manage-orders/cancel/{id}")
    public String cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
        return "redirect:/manage-orders";
    }
}
