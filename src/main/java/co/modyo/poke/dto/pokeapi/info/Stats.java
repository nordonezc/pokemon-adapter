package co.modyo.poke.dto.pokeapi.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represent each stat of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class Stats {

    /**
     * It has the initial value of the stat
     */
    @JsonProperty("base_stat")
    private Integer baseStat;

    /**
     * It has the name of the stat
     */
    private Stat stat;
}
