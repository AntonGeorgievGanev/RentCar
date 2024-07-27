package bg.rentacar.web;

import bg.rentacar.model.entity.User;
import bg.rentacar.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testGetRegister() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testPostRegister() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "test")
                        .param("firstName", "Test")
                        .param("lastName", "Test")
                        .param("email", "test@test.com")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("phoneNumber", "1134568890")
                        .param("age", "33")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        Optional<User> userOpt = userRepository.findByEmail("test@test.com");

        Assertions.assertTrue(userOpt.isPresent());

        User user = userOpt.get();

        Assertions.assertEquals("Test", user.getFirstName());
        Assertions.assertEquals("Test", user.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("test123", user.getPassword()));
    }
}
