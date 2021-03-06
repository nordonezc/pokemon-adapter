package co.modyo.poke.controller;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.services.PokeService;
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

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PokeApiController.class)
public class PokeApiAdapterServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PokeService pokeService;

    @Test
    public void countPokemon_whenCalled_shouldReturnInteger() throws Exception {

        doReturn(900).when(pokeService).countPokemon();

        mvc.perform(MockMvcRequestBuilders
                .get("/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
    }

    @Test
    public void getPokemon_whenValidID_shouldValidPokemon() throws Exception {

        Integer requestID = 1;

        doReturn(Pokemon.builder().name("Charmander").build())
                .when(pokeService).getPokemonInfo(requestID);

        mvc.perform(MockMvcRequestBuilders
                .get("/pokemon/"+requestID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    public void getEvolution_whenValidID_shouldValidEvolution() throws Exception {

        Integer requestID = 1;

        doReturn(new ArrayList<>())
                .when(pokeService).getEvolutionInfo(requestID);

        mvc.perform(MockMvcRequestBuilders
                .get("/evolution/"+requestID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

}
