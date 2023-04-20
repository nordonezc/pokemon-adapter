package co.modyo.poke.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Represent all the {@link Pokemon} involved in the the evolution
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
@Builder
public class EvolutionInfo {

  /**
   * Name of the {@link Pokemon} that is involved in the evolution process
   */
  private String name;

  /**
   * {@link LinkedID} of the {@link Pokemon} that is involved in the evolution process
   */
  private LinkedID pokemonID;
}
