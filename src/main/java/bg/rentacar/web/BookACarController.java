package bg.rentacar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookACarController {

    @GetMapping("/book-car")
    public String rent(){
        return "book-car";
    }
}
