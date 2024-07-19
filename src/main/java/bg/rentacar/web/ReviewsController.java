package bg.rentacar.web;

import bg.rentacar.model.dto.AllReviewsDTO;
import bg.rentacar.model.dto.ReviewDTO;
import bg.rentacar.service.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ReviewsController {

    private final ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ModelAttribute("reviewDTO")
    private ReviewDTO reviewDTO(){
        return new ReviewDTO();
    }

    @ModelAttribute("allReviewsDTO")
    private AllReviewsDTO allReviewsDTO(){
        return new AllReviewsDTO();
    }

    @GetMapping("/customers-review")
    public String reviews(Model model){
        model.addAttribute("allReviewsDTO", reviewService.fetchAllReviews());
        return "customers-review";
    }

    @GetMapping("/add-review")
    public String addReview(){
        return "add-review";
    }

    @PostMapping("/add-review")
    public String addReview(Principal principal, @Valid ReviewDTO reviewDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("reviewDTO", reviewDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewDTO", bindingResult);
            return "redirect:/add-review";
        }
        reviewService.addReview(principal, reviewDTO);
        return "redirect:/customers-review";
    }

    @GetMapping("/my-reviews")
    public String myReviews(Principal principal, Model model){
        model.addAttribute("allReviewsDTO", reviewService.getUserReviews(principal));
        return "my-reviews";
    }

    @DeleteMapping("/my-reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return "redirect:/my-reviews";
    }
}
