package bg.rentacar.web;

import bg.rentacar.model.dto.ReviewDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.service.ReviewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void initUser(){
        User user = new User();
        user.setUsername("test");
        user.setFirstName("Anton");
        user.setLastName("Ganev");
        user.setAge(33);
        user.setEmail("test@mail.com");
        user.setPhoneNumber("5656565656");
        user.setPassword("test123");
        userRepository.save(user);
    }

    @AfterEach
    void clear(){
        userRepository.deleteAll();
    }

    @Test
    void testGetCustomersReviews() throws Exception {
        mockMvc.perform(get("/customers-review"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers-review"));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void testGetAddReview() throws Exception {
        mockMvc.perform(get("/add-review"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-review"));
    }

//    @Test
//    @WithMockUser(username = "test", roles = {"USER"})
//    void testAddReview_Success() throws Exception {
//
//        ReviewDTO reviewDTO = new ReviewDTO();
//        reviewDTO.setTitle("Test title");
//        reviewDTO.setDescription("Test description");
//        reviewDTO.setRating(4);
//
//        mockMvc.perform(post("/add-review")
//                        .param("title", reviewDTO.getTitle())
//                        .param("description", reviewDTO.getDescription())
//                        .param("rating", String.valueOf(reviewDTO.getRating()))
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/customers-review"));
//    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void testAddReview_Invalid() throws Exception {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle("");
        reviewDTO.setDescription("T");
        reviewDTO.setRating(-1);

        mockMvc.perform(post("/add-review")
                        .param("title", reviewDTO.getTitle())
                        .param("description", reviewDTO.getDescription())
                        .param("rating", String.valueOf(reviewDTO.getRating()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/add-review"));
    }

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void testGetUserReviews() throws Exception {
        mockMvc.perform(get("/my-reviews").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("my-reviews"));
    }

    @Test
    void testGetReviewById(){
        ReviewDTO reviewDTO = reviewService.getReviewById(1L);
        Assertions.assertEquals("Excellent", reviewDTO.getTitle());
        Assertions.assertEquals("Anton Ganev", reviewDTO.getAuthor());
    }
}
