package bg.rentacar.web;

import bg.rentacar.model.dto.ReviewDTO;
import bg.rentacar.model.entity.User;
import bg.rentacar.repository.UserRepository;
import bg.rentacar.service.ReviewService;
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

    @Test
    @WithMockUser(username = "test", roles = {"USER"})
    void testAddReview() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setAge(33);
        user.setEmail("test@mail.com");
        user.setPhoneNumber("5656565656");
        user.setPassword("test123");
        userRepository.save(user);

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle("Test title");
        reviewDTO.setDescription("Test description");
        reviewDTO.setRating(4);

        mockMvc.perform(post("/add-review")
                        .param("title", reviewDTO.getTitle())
                        .param("description", reviewDTO.getDescription())
                        .param("rating", String.valueOf(reviewDTO.getRating()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers-review"));
    }
}
