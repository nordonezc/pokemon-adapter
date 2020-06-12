package co.modyo.poke.services;

import co.modyo.poke.adapter.services.PokeApiAdapter;
import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.PokemonList;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import co.modyo.poke.mapper.EvolutionInfoMapper;
import co.modyo.poke.mapper.PokemonMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static co.modyo.poke.utils.HttpUtils.setInputEntity;
import static javax.management.timer.Timer.ONE_DAY;
import static javax.management.timer.Timer.ONE_WEEK;

/**
 * Use to consume the external pokemon API
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PokeApiServiceImpl implements PokeService {

    /**
     * Consume adapter to bring the pokemon information
     */
    public final PokeApiAdapter apiAdapter;

    private final Logger logger = LoggerFactory.getLogger(PokeApiServiceImpl.class);

    /**
     * {@inheritDoc}
     *
     * @see PokeService#countPokemon()
     */
    @Override
    @Cacheable("count")
    public Integer countPokemon() {

        return apiAdapter.countPokemon();
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeService#getPokemonInfo(Integer)
     */
    @Override
    @Caching(
            put = {
                    @CachePut(value = "pokemon-info", condition = "#id<100"),
                    @CachePut(value = "pokemon-info", condition = "#id>900"),
            }
    )
    public Pokemon getPokemonInfo(Integer id) {

        BasicInfo basicInfo = apiAdapter.getBasicInfo(id);
        SpeciesInfo speciesInfo = apiAdapter.getSpeciesInfo(id);

        return PokemonMapper.map(
                Objects.requireNonNull(basicInfo),
                Objects.requireNonNull(speciesInfo)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeService#getEvolutionInfo(Integer)
     */
    @Override
    @CachePut(value = "evolutions", condition = "#id<50")
    public List<Evolution> getEvolutionInfo(Integer id) {

        EvolutionDetail adapterResponse = apiAdapter.getEvolutionDetail(id);

        return EvolutionInfoMapper.map(
                Objects.requireNonNull(adapterResponse)
        );
    }

    /**
     * Clear the count cache every month, because the amount of pokemon is not usual to change
     */
    @Scheduled(fixedDelay = ONE_WEEK*4)
    @CacheEvict("count")
    public void clearCountCache() {

    }

}
