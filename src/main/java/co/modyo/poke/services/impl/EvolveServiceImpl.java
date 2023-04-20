package co.modyo.poke.services.impl;

import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.services.PokeApiAdapterService;
import co.modyo.poke.dto.Evolution;
import co.modyo.poke.mapper.EvolutionInfoMapper;
import co.modyo.poke.services.EvolveService;
import co.modyo.poke.services.PokeService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvolveServiceImpl implements EvolveService {

  /**
   * Consume adapter to bring the pokemon information
   */
  public final PokeApiAdapterService apiAdapter;

  private final Logger logger = LoggerFactory.getLogger(
    EvolveServiceImpl.class
  );

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
    logger.info(
      "The given {} return valid answer for consult evolution detail: {}",
      id,
      adapterResponse != null
    );

    logger.info(
      "Init map the response of evolution detail for the evolution {}",
      id
    );
    List<Evolution> evolutionList = EvolutionInfoMapper.map(
      Objects.requireNonNull(adapterResponse)
    );
    logger.info(
      "The evolution id {} give a list with valid size: {}",
      id,
      evolutionList.isEmpty()
    );

    return evolutionList;
  }
}
