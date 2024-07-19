package bg.rentacar.model.dto;

import bg.rentacar.constant.ReviewConstants;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ReviewDTO {

    private Long id;

    @NotBlank(message = ReviewConstants.REVIEW_TITLE_EMPTY)
    @Size(min = 5, max = 50, message = ReviewConstants.REVIEW_TITLE_LENGTH)
    private String title;

    @NotNull(message = ReviewConstants.REVIEW_RATING_EMPTY)
    @Min(value = 1, message = ReviewConstants.REVIEW_RATING_MIN)
    @Max(value = 5, message = ReviewConstants.REVIEW_RATING_MAX)
    private Integer rating;

    @NotBlank(message = ReviewConstants.REVIEW_DESC_EMPTY)
    @Size(min = 5, max = 250, message = ReviewConstants.REVIEW_DESC_LENGTH)
    private String description;

    private String author;

    private Long userId;

    private LocalDate published;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
