package co.modyo.poke.adapter.dto;

import co.modyo.poke.adapter.dto.info.Abilities;
import co.modyo.poke.adapter.dto.info.Sprites;
import co.modyo.poke.adapter.dto.info.Stats;
import co.modyo.poke.adapter.dto.info.Types;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Represent the attributes of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class BasicInfo {

  /**
   * The {@link List} of {@link Abilities} that the pokemon can do
   */
  private List<Abilities> abilities;

  /**
   * The initial value of the experience
   */
  @JsonProperty("base_experience")
  private Integer baseExperience;

  /**
   * The default height
   */
  private Integer height;

  /**
   * The name
   */
  private String name;

  /**
   * The {@link List} of {@link Types} with his name types
   */
  private List<Types> types;

  /**
   * The default weight
   */
  private Integer weight;

  /**
   * The {@link List} of {@link Stats} with his respective value
   */
  private List<Stats> stats;

  /**
   * The sprites available for the pokemon
   */
  private Sprites sprites;
}
