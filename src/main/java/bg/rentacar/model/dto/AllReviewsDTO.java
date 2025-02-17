package bg.rentacar.model.dto;

import java.util.List;

public class AllReviewsDTO {
    private List<ReviewDTO> reviews;

    public AllReviewsDTO() {
    }

    public AllReviewsDTO(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
