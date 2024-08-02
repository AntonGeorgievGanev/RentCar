package bg.rentacar.service;

import bg.rentacar.model.dto.AllReviewsDTO;
import bg.rentacar.model.dto.ReviewDTO;

import java.security.Principal;

public interface ReviewService {
    void addReview(Principal principal, ReviewDTO reviewDTO);
    AllReviewsDTO fetchAllReviews();
    AllReviewsDTO getUserReviews(Principal principal);
    void deleteReview(Long id);
}
