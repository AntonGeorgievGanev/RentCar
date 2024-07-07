package bg.rentacar.web;

import bg.rentacar.model.dto.ExtraDTO;
import bg.rentacar.service.extra.ExtraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddExtraController {

    private final ExtraService extraService;

    public AddExtraController(ExtraService extraService) {
        this.extraService = extraService;
    }

    @ModelAttribute("addExtraDTO")
    private ExtraDTO addExtraDTO(){
        return new ExtraDTO();
    }

    @GetMapping("/add-extra")
    public String addExtra(){
        return "add-extra";
    }

    @PostMapping("/add-extra")
    public String addExtra(@Valid ExtraDTO addExtraDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addExtraDTO", addExtraDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addExtraDTO", bindingResult);

            return "redirect:/add-extra";
        }

        extraService.addExtra(addExtraDTO);
        return "redirect:/";
    }
}
