package bg.rentacar.service.review.impl;

import bg.rentacar.model.dto.AllReviewsDTO;
import bg.rentacar.model.dto.ReviewDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.service.review.ReviewService;
import bg.rentacar.service.user.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

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
                .accept(MediaType.APPLICATION_JSON)
                .body(reviewDTO)
                .retrieve();
    }

    @Override
    public AllReviewsDTO fetchAllReviews() {
        List<ReviewDTO> allReviews = restClient.get()
                .uri("http://localhost:8081/api/reviews")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});

        return new AllReviewsDTO(allReviews);
    }

    @Override
    public AllReviewsDTO getUserReviews(Principal principal) {
        User user = userService.getUserByName(principal.getName());

        List<ReviewDTO> allReviews = restClient.get()
                .uri("http://localhost:8081/api/reviews")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});


        assert allReviews != null;
        List<ReviewDTO> userReviews = allReviews.stream().filter(r -> r.getUserId().equals(user.getId())).toList();

        return new AllReviewsDTO(userReviews);
    }

    @Override
    public void deleteReview(Long id) {
        restClient.delete()
                .uri("http://localhost:8081/api/reviews/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
