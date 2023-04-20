package co.modyo.poke.services;

import co.modyo.poke.dto.Evolution;
import java.util.List;

public interface EvolveService {
  /**
   * It will return the complete info of the pokemon evolution
   *
   * @param id The id of the pokemon in the pokemon api
   * @return The {@link List} of {@link Evolution} with its properties
   */
  List<Evolution> getEvolutionInfo(Integer id);
}
