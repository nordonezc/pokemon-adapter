package co.modyo.poke.mapper;

import co.modyo.poke.dto.pokeapi.SpeciesInfo;
import co.modyo.poke.dto.pokeapi.evolution.EvolutionChain;

import static co.modyo.poke.mapper.PokemonMapper.getIDFromURL;

/**
 * Use to transform the type given chain evolution to the name of the
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class EvolutionChainMapper {

    /**
     * Transform the {@link SpeciesInfo} into a integer with the number of the
     * evolution chain. 
     * @see PokemonMapper#getIDFromURL(String)
     *
     * @param evolutionChain - The {@link Characteristics} given by the pokemon API+
     * @return The number of the evolution chain
     */
    protected static Integer map(EvolutionChain evolutionChain) {

        return getIDFromURL(evolutionChain.getUrl());
    }
}
