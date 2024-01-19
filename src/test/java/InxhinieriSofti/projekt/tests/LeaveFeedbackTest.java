package InxhinieriSofti.projekt.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class LeaveFeedbackTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

   @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testLeaveFeedback() throws Exception {
       Long courseId = 1L;
       mockMvc.perform(MockMvcRequestBuilders.get("/leave-feedback/{courseId}", courseId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("LeaveFeedback"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courseId"));
    }

    @Test
    public void testSubmitFeedback() throws Exception {
        // Replace {courseId} with the actual course ID for testing
        Long courseId = 1L;

       String description = "Great course!";
        int rating = 5;

       mockMvc.perform(MockMvcRequestBuilders.post("/leave-feedback")
                        .param("courseId", String.valueOf(courseId))
                        .param("description", description)
                        .param("rating", String.valueOf(rating)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses/" + courseId));
    }
}
