package co.modyo.poke.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.modyo.poke.services.EvolveService;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EvolutionController.class)
public class EvolutionControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private EvolveService evolveService;

  @Test
  public void getEvolution_whenValidID_shouldValidEvolution() throws Exception {
    Integer requestID = 1;

    doReturn(new ArrayList<>()).when(evolveService).getEvolutionInfo(requestID);

    mvc
      .perform(
        MockMvcRequestBuilders
          .get("/evolution/" + requestID)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().is2xxSuccessful())
      .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
  }
}
