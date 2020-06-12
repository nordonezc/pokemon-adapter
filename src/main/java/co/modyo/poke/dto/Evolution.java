package co.modyo.poke.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Represent all the {@link EvolutionInfo} of the {@link Pokemon}
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Evolution {

    /**
     * The {@link EvolutionInfo} of the {@link Pokemon} that is involved in the evolution process
     */
    private List<EvolutionInfo> informationDetail;
}
