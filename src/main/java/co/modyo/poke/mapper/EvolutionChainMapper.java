package co.modyo.poke.mapper;

import co.modyo.poke.dto.LinkedID;
import co.modyo.poke.adapter.dto.evolution.EvolutionChain;

import static co.modyo.poke.mapper.PokemonMapper.getLinkedIDFromURL;

/**
 * Use to transform the type given chain evolution to the name of the
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class EvolutionChainMapper {

    /**
     * Transform the {@link EvolutionChain} into a integer with the number of the
     * evolution chain.
     *
     * @param evolutionChain - The {@link EvolutionChain} given by the pokemon API+
     * @return The number of the evolution chain
     * @see PokemonMapper#getLinkedIDFromURL(String, boolean)
     */
    protected static LinkedID map(EvolutionChain evolutionChain) {

        return getLinkedIDFromURL(evolutionChain.getUrl(), false);
    }
}
