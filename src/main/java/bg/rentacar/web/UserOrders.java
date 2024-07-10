package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserOrders {

    @GetMapping("/my-orders")
    public String userOrders(){
        return "user-orders";
    }
}
