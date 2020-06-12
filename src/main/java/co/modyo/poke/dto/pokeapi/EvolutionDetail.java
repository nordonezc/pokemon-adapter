package co.modyo.poke.dto.pokeapi;

import co.modyo.poke.dto.pokeapi.evolution.Chain;
import lombok.Data;

/**
 * Represent the detail information of the evolution
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class EvolutionDetail {

    /**
     * The complete evolution {@link Chain}
     */
    private Chain chain;

}
