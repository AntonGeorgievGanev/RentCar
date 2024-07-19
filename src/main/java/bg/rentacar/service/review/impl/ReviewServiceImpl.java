package bg.rentacar.service.review.impl;

import bg.rentacar.model.dto.ReviewDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.service.review.ReviewService;
import bg.rentacar.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RestClient restClient;
    private final UserService userService;

    public ReviewServiceImpl(RestClient restClient, UserService userService) {
        this.restClient = restClient;
        this.userService = userService;
    }

    @Override
    public void addReview(Principal principal, ReviewDTO reviewDTO) {
        reviewDTO.setPublished(LocalDate.now());
        User user = userService.getUserByName(principal.getName());
        reviewDTO.setAuthor(user.getFirstName() + " " + user.getLastName());
        reviewDTO.setUserId(user.getId());
        restClient.post()
                .uri("http://localhost:8081/api/reviews")
                .body(reviewDTO)
                .retrieve();
    }
}
