package co.modyo.poke.adapter.services;

import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.PokemonList;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static co.modyo.poke.utils.HttpUtils.setInputEntity;

/**
 * Use to consume the external pokemon API
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PokeApiAdapterServiceImpl implements PokeApiAdapterService {


    /**
     * URL of the pokemon api
     */
    @Value("${poke.api.url}")
    private String urlPokeAPI;

    /**
     * Use to consume external service
     */
    private final RestTemplate restTemplate;

    /**
     * Use to log the transactions
     */
    private final Logger logger = LoggerFactory.getLogger(PokeApiAdapterServiceImpl.class);

    /**
     * {@inheritDoc}
     *
     * @see PokeApiAdapterService#countPokemon()
     */
    @Override
    public Integer countPokemon() {

        return Try.of(() ->

                restTemplate.exchange(
                        urlPokeAPI + "pokemon",
                        HttpMethod.GET,
                        setInputEntity(null),
                        PokemonList.class)
                        .getBody().getCount()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeApiAdapterService#getSpeciesInfo(Integer)
     */
    @Override
    public SpeciesInfo getSpeciesInfo(Integer id) {

        return Try.of(() ->

                restTemplate.exchange(
                        urlPokeAPI + "pokemon-species/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        SpeciesInfo.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeApiAdapterService#getBasicInfo(Integer)
     */
    public BasicInfo getBasicInfo(Integer id) {

        return Try.of(() ->

                restTemplate.exchange(
                        urlPokeAPI + "pokemon/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        BasicInfo.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeApiAdapterService#getEvolutionDetail(Integer)
     */
    @Override
    public EvolutionDetail getEvolutionDetail(Integer id) {

        return Try.of(() ->

                restTemplate.exchange(
                        urlPokeAPI + "evolution-chain/" + id,
                        HttpMethod.GET,
                        setInputEntity(null),
                        EvolutionDetail.class).getBody()
        ).recover(Exception.class, e -> null).get();
    }
}
