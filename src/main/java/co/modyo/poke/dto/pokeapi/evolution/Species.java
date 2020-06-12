package co.modyo.poke.dto.pokeapi.evolution;

import lombok.Data;

/**
 * Represent the basic info for the pokemon evolutions
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class Species {

    /**
     * The name of the next evolution
     */
    private String name;

    /**
     * The url to consult the information of the evolution
     */
    private String url;
}
