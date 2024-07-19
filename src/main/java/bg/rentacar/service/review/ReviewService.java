package bg.rentacar.service.review;

import bg.rentacar.model.dto.ReviewDTO;

import java.security.Principal;

public interface ReviewService {
    void addReview(Principal principal, ReviewDTO reviewDTO);
}
