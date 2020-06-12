package co.modyo.poke.adapter.services;

import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.PokemonList;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import co.modyo.poke.services.PokeApiServiceImpl;
import io.vavr.control.Try;
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
public class PokeApiAdapterImpl implements PokeApiAdapter {


    @Value("${poke.api.url}")
    private String urlPokeAPI;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Logger logger = LoggerFactory.getLogger(PokeApiAdapterImpl.class);

    /**
     * {@inheritDoc}
     *
     * @see PokeApiAdapter#countPokemon()
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
     * @see PokeApiAdapter#getSpeciesInfo(Integer)
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
     * @see PokeApiAdapter#getBasicInfo(Integer)
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
     * @see PokeApiAdapter#getEvolutionDetail(Integer)
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
