package co.modyo.poke.adapter.dto.evolution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Represent the evolution chain of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class Chain {

    /**
     * The name and the url of the evolution of the pokemon
     */
    private Species species;

    /**
     * The evolutions which the pokemon could {@link Chain}
     */
    @JsonProperty("evolves_to")
    private List<Chain> evolvesTo;
}
