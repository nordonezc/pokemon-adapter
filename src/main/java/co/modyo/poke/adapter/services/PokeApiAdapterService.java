package co.modyo.poke.adapter.services;

import co.modyo.poke.adapter.dto.BasicInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.SpeciesInfo;

/**
 * Creates a uniform way to return the needed consults for pokemons
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public interface PokeApiAdapterService {

    /**
     * Get number of pokemon available in the pokemon API
     *
     * @return The amount of pokemon in the pokemon API
     */
    Integer countPokemon();

    /**
     * Get characteristics of the pokemon
     *
     * @param id - id of the pokemon to consult
     * @return The {@link SpeciesInfo} of the pokemon
     */
    SpeciesInfo getSpeciesInfo(Integer id);

    /**
     * Get basic info of the pokemon
     *
     * @param id - id of the pokemon to consult
     * @return The {@link BasicInfo} of the pokemon
     */
    BasicInfo getBasicInfo(Integer id);

    /**
     * Get evolution process of the pokemon
     *
     * @param id - id of the evolution chain to consult
     * @return The {@link EvolutionDetail} of the id given
     */
    EvolutionDetail getEvolutionDetail(Integer id);
}
