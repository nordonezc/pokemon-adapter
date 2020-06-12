package co.modyo.poke.services;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.EvolutionInfo;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.dto.pokeapi.BasicInfo;

import java.util.List;

/**
 * Expose the operations that will be consumed by the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public interface PokeService {

    /**
     * It will return the list of all pokemon
     *
     * @param id The id of the pokemon in the pokemon api
     * @return The {@link BasicInfo} with its properties
     */
    Object getPokemonInfo(Integer id);

    /**
     * It will return the complete info of the pokemon evolution
     *
     * @param id The id of the pokemon in the pokemon api
     * @return The {@link List} of {@link Evolution} with its properties
     */
    List<Evolution> getEvolutionInfo(Integer id);

}
