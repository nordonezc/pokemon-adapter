package co.modyo.poke.adapter.dto;

import lombok.Data;

/**
 * Represent the amount of pokemon to recover from the pokemon api
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class PokemonList {

    /**
     * Amount of pokemon in the pokemon api
     */
    private Integer count;
}
