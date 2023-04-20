package co.modyo.poke.services;

import static co.modyo.poke.utils.JsonFileUtils.readJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import co.modyo.poke.adapter.services.PokeApiAdapterServiceImpl;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.services.impl.PokeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test of {@link PokeServiceImpl}
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
  classes = { PokeServiceImpl.class, PokeApiAdapterServiceImpl.class }
)
public class PokeServiceTest {

  /**
   * Instance of the class under test
   */
  @InjectMocks
  public PokeServiceImpl pokeService;

  @MockBean
  public PokeApiAdapterServiceImpl apiAdapterService;

  @Before
  public void init() {
    pokeService = new PokeServiceImpl(apiAdapterService);
  }

  @Test
  public void countPokemon_whenCall_shouldReturnIntegerGivenByPokeAPI() {
    Integer expectedResponse = 900;
    when(apiAdapterService.countPokemon()).thenReturn(expectedResponse);

    Integer responseGiven = pokeService.countPokemon();

    assertThat(responseGiven).isEqualTo(expectedResponse);
  }

  @Test
  public void getPokemonInfo_whenCallWithValidID_shouldReturnValidPokemon() {
    Integer givenID = 900;
    BasicInfo expectedBasicInfo = readJsonFile(
      "/basic-info.json",
      BasicInfo.class
    );
    SpeciesInfo expectedSpecies = readJsonFile(
      "/pokemon-species.json",
      SpeciesInfo.class
    );

    when(apiAdapterService.getBasicInfo(givenID)).thenReturn(expectedBasicInfo);
    when(apiAdapterService.getSpeciesInfo(givenID)).thenReturn(expectedSpecies);

    Pokemon pokemonReturned = pokeService.getPokemonInfo(givenID);

    assertThat(pokemonReturned)
      .isNotNull()
      .hasFieldOrProperty("description")
      .hasFieldOrProperty("evolutionID")
      .hasFieldOrProperty("abilities")
      .hasFieldOrProperty("stats")
      .hasFieldOrProperty("types")
      .hasFieldOrProperty("image")
      .hasFieldOrProperty("baseExperience")
      .hasFieldOrProperty("height")
      .hasFieldOrProperty("weight")
      .hasFieldOrProperty("name");
  }
}
