package co.modyo.poke.dto.pokeapi.info;

import lombok.Data;


/**
 * Represent each ability of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class Abilities {

    /**
     * The {@link Ability} properties
     */
    private Ability ability;
}
