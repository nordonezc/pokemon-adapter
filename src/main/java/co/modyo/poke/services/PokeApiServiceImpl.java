package co.modyo.poke.services;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.EvolutionInfo;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.dto.pokeapi.BasicInfo;
import co.modyo.poke.dto.pokeapi.EvolutionDetail;
import co.modyo.poke.dto.pokeapi.SpeciesInfo;
import co.modyo.poke.mapper.EvolutionInfoMapper;
import co.modyo.poke.mapper.PokemonMapper;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * Use to consume the external pokemon API
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Service
public class PokeApiServiceImpl implements PokeService {

    @Value("${poke.api.url}")
    private String urlPokeAPI;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(PokeApiServiceImpl.class);

    /**
     * {@inheritDoc}
     * @see PokeService#getPokemonInfo(Integer)
     */
    @Override
    @Cacheable(value="pokemon-info", condition="#id<100")
    public Pokemon getPokemonInfo(Integer id) {
        return PokemonMapper.map(
                Objects.requireNonNull(getBasicInfo(id)),
                Objects.requireNonNull(getSpeciesInfo(id))
        );
    }

    /**
     * {@inheritDoc}
     * @see PokeService#getEvolutionInfo(Integer)
     */
    @Override
    @Cacheable("evolutions")
    public List<Evolution> getEvolutionInfo(Integer id) {
        return EvolutionInfoMapper.map(
                Objects.requireNonNull(getEvolutionDetail(id))
        );
    }

    // ------------------------------------------------------
    // INNER LOGIC
    // ------------------------------------------------------

    /**
     * Get characteristics of the pokemon
     *
     * @param id - id of the pokemon to consult
     * @return The {@link SpeciesInfo} of the pokemon
     */
    private SpeciesInfo getSpeciesInfo(Integer id) {
        return Try.of(() ->
                restTemplate.exchange(
                        urlPokeAPI + "pokemon-species/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        SpeciesInfo.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * Get basic info of the pokemon
     *
     * @param id - id of the pokemon to consult
     * @return The {@link BasicInfo} of the pokemon
     */
    private BasicInfo getBasicInfo(Integer id) {
        return Try.of(() ->
                restTemplate.exchange(
                        urlPokeAPI + "pokemon/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        BasicInfo.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * Get evolution process of the pokemon
     *
     * @param id - id of the evolution chain to consult
     * @return The {@link EvolutionDetail} of the id given
     */
    private EvolutionDetail getEvolutionDetail(Integer id) {
        return Try.of(() ->
                restTemplate.exchange(
                        urlPokeAPI + "evolution-chain/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        EvolutionDetail.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * Set the basic {@link HttpHeaders} with
     * <ul>
     *     <li>{@link HttpHeaders#USER_AGENT} with the name of the application</li>
     *     <li>{@link HttpHeaders#ACCEPT} with {@link MediaType#APPLICATION_JSON}</li>
     *     <li>{@link HttpHeaders#CONTENT_TYPE} with {@link MediaType#APPLICATION_JSON}</li>
     * </ul>
     *
     * @param input The object to be embedded in the {@link HttpEntity} in case is needed
     * @param <T>   Use of generics to accept any kind of Object
     * @return A {@link HttpEntity} with the needed headers
     */
    private <T> HttpEntity setInputEntity(T input) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        httpHeaders.add(HttpHeaders.USER_AGENT, "pokedex");
        return new HttpEntity(input, httpHeaders);
    }

}
