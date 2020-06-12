package co.modyo.poke.adapter.service;

import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.PokemonList;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import co.modyo.poke.adapter.dto.evolution.EvolutionChain;
import co.modyo.poke.adapter.services.PokeApiAdapterServiceImpl;
import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static co.modyo.poke.utils.HttpUtils.setInputEntity;
import static co.modyo.poke.utils.JsonFileUtils.readJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Test of {@link PokeApiAdapterServiceImpl}
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PokeApiAdapterServiceImpl.class})
public class PokeApiServiceTest {

    /**
     * Instance of the class under test
     */
    @InjectMocks
    public PokeApiAdapterServiceImpl pokeService;

    @MockBean
    public RestTemplate restTemplate;

    @Before
    public void init() {
        pokeService = new PokeApiAdapterServiceImpl(restTemplate);
    }

    @Test
    public void countPokemon_whenCall_shouldReturnIntegerGivenByPokeAPI() {
        PokemonList pokemonList = readJsonFile("/pokemon-list.json", PokemonList.class);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(setInputEntity(null)),
                eq(PokemonList.class)))
                .thenReturn(new ResponseEntity<>(pokemonList, HttpStatus.OK));

        Integer responseGiven = pokeService.countPokemon();

        assertThat(responseGiven)
                .isEqualTo(pokemonList.getCount());
    }

    @Test
    public void countPokemon_withValidID_shouldReturnSameResponseOfPokeAPI() {
        SpeciesInfo speciesInfo = readJsonFile("/pokemon-species.json", SpeciesInfo.class);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(setInputEntity(null)),
                eq(SpeciesInfo.class)))
                .thenReturn(new ResponseEntity<>(speciesInfo, HttpStatus.OK));

        SpeciesInfo responseGiven = pokeService.getSpeciesInfo(0);

        assertThat(responseGiven)
                .isEqualTo(speciesInfo);
    }

    @Test
    public void getBasicInfo_withValidID_shouldReturnSameResponseOfPokeAPI() {
        BasicInfo basicInfo = readJsonFile("/basic-info.json", BasicInfo.class);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(setInputEntity(null)),
                eq(BasicInfo.class)))
                .thenReturn(new ResponseEntity<>(basicInfo, HttpStatus.OK));

        BasicInfo responseGiven = pokeService.getBasicInfo(0);

        assertThat(responseGiven)
                .isEqualTo(basicInfo);
    }


    @Test
    public void getDetailEvolution_withValidID_shouldReturnSameResponseOfPokeAPI() {
        EvolutionDetail evolutionDetail = readJsonFile("/evolution-chain.json", EvolutionDetail.class);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(setInputEntity(null)),
                eq(EvolutionDetail.class)))
                .thenReturn(new ResponseEntity<>(evolutionDetail, HttpStatus.OK));

        EvolutionDetail responseGiven = pokeService.getEvolutionDetail(0);

        assertThat(responseGiven)
                .isEqualTo(evolutionDetail);
    }

}
