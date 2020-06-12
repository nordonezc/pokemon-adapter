package co.modyo.poke.adapter.dto;

import co.modyo.poke.adapter.dto.evolution.Chain;
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
