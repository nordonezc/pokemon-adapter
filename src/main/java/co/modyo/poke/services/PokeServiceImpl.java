package co.modyo.poke.services;

import co.modyo.poke.adapter.services.PokeApiAdapterService;
import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.SpeciesInfo;
import co.modyo.poke.mapper.EvolutionInfoMapper;
import co.modyo.poke.mapper.PokemonMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static javax.management.timer.Timer.ONE_WEEK;

/**
 * Use to consume the external pokemon API
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PokeServiceImpl implements PokeService {

    /**
     * Consume adapter to bring the pokemon information
     */
    public final PokeApiAdapterService apiAdapter;

    private final Logger logger = LoggerFactory.getLogger(PokeServiceImpl.class);

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

        logger.info("Init consult basic info of the pokemon with {}", id);
        BasicInfo basicInfo = apiAdapter.getBasicInfo(id);
        logger.info("The given {} return valid answer: {}", id, basicInfo != null);

        logger.info("Init species info of the pokemon with {}", id);
        SpeciesInfo speciesInfo = apiAdapter.getSpeciesInfo(id);
        logger.info("The given {} return valid answer: {}", id, speciesInfo != null);

        logger.info("Init map api response to info needed with {}", id);
        Pokemon pokemon = PokemonMapper.map(
                Objects.requireNonNull(basicInfo),
                Objects.requireNonNull(speciesInfo)
        );
        logger.info("The given {} return valid answer: {}", id, pokemon != null);

        return pokemon;
    }

    /**
     * {@inheritDoc}
     *
     * @see PokeService#getEvolutionInfo(Integer)
     */
    @Override
    @CachePut(value = "evolutions", condition = "#id<50")
    public List<Evolution> getEvolutionInfo(Integer id) {

        logger.info("Init consult evolution detail for the id {}", id);
        EvolutionDetail adapterResponse = apiAdapter.getEvolutionDetail(id);
        logger.info("The given {} return valid answer for consult evolution detail: {}",
                id,
                adapterResponse != null);

        logger.info("Init map the response of evolution detail for the evolution {}", id);
        List<Evolution> evolutionList = EvolutionInfoMapper.map(
                Objects.requireNonNull(adapterResponse)
        );
        logger.info("The evolution id {} give a list with valid size: {}",
                id,
                evolutionList.isEmpty());

        return evolutionList;
    }

    /**
     * Clear the count cache every month, because the amount of pokemon is not usual to change
     */
    @Scheduled(fixedDelay = ONE_WEEK * 4)
    @CacheEvict("count")
    public void clearCountCache() {

    }

}
