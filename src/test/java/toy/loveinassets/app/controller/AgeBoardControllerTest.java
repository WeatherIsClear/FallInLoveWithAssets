package toy.loveinassets.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AgeBoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void ageBoardList() throws Exception {
        this.mockMvc.perform(get("/age-board/1")
                        .param("page", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("ageboard/ageBoards"));
    }

}