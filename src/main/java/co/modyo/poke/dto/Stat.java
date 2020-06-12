package co.modyo.poke.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Represent each stat of the pokemon with his respective base value
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
@Builder
public class Stat {

    /**
     * The name of the stat
     */
    private String name;

    /**
     * It has the initial value of the stat
     */
    private Integer baseStat;
}
