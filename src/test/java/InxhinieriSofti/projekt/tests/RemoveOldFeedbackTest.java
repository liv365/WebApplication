package InxhinieriSofti.projekt.tests;

import org.hamcrest.Matchers;
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
public class RemoveOldFeedbackTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testRemoveOldFeedback() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/remove-old-feedback"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("RemoveOldFeedbackPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("removedFeedbackCount"))
                .andExpect(MockMvcResultMatchers.model().attribute("removedFeedbackCount", Matchers.greaterThanOrEqualTo(0)));
    }

}
