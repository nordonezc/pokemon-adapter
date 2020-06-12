package co.modyo.poke.adapter.dto;

import co.modyo.poke.adapter.dto.description.FlavorTextEntries;
import co.modyo.poke.adapter.dto.evolution.EvolutionChain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Represent the chain evolution of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class SpeciesInfo {

    /**
     * Represent in what {@link EvolutionChain} is the pokemon
     */
    @JsonProperty("evolution_chain")
    private EvolutionChain evolutionChain;

    /**
     * {@link List} of {@link FlavorTextEntries} with the description of the pokemon
     */
    @JsonProperty("flavor_text_entries")
    private List<FlavorTextEntries> flavorTextEntries;

}
