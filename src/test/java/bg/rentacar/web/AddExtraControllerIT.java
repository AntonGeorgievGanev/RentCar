package bg.rentacar.web;

import bg.rentacar.model.entity.Extra;
import bg.rentacar.repository.ExtraRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
public class AddExtraControllerIT {
    @Autowired
    private ExtraRepository extraRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void clean(){
        extraRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetExtra() throws Exception {
        mockMvc.perform(get("/add-extra"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-extra"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddExtra() throws Exception {
        mockMvc.perform(post("/add-extra")
                .param("name", "test")
                .param("price", "100")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Assertions.assertTrue(extraRepository.findById(1L).isPresent());
        Extra extra = extraRepository.findById(1L).get();
        Assertions.assertEquals("test", extra.getName());
        Assertions.assertEquals(100, extra.getPrice().intValue());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAddExtra_invalid() throws Exception {
        mockMvc.perform(post("/add-extra")
                        .param("name", "t")
                        .param("price", "-100")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/add-extra"));
    }
}
