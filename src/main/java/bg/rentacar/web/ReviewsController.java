package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewsController {
    @GetMapping("/users-review")
    public String reviews(){
        return "users-review";
    }
}
