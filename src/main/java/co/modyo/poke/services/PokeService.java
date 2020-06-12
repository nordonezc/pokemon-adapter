package co.modyo.poke.services;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;

import java.util.List;

/**
 * Expose the operations that will be consumed by the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public interface PokeService {

    /**
     * It quantity of pokemon available to consult
     *
     * @return The amount of pokemon
     */
    Integer countPokemon();

    /**
     * It will return the list of all pokemon
     *
     * @param id The id of the pokemon in the pokemon api
     * @return The {@link Pokemon} with its properties
     */
    Pokemon getPokemonInfo(Integer id);

    /**
     * It will return the complete info of the pokemon evolution
     *
     * @param id The id of the pokemon in the pokemon api
     * @return The {@link List} of {@link Evolution} with its properties
     */
    List<Evolution> getEvolutionInfo(Integer id);

}
