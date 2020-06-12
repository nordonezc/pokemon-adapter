package co.modyo.poke.controller;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.EvolutionInfo;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.services.PokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expose the operations that will be consumed by the front
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PokeApiController {

    /**
     * Service to handle the consult of pokemon
     * @see PokeService
     */
    private final PokeService pokeService;

    /**
     * Get the pokemon basic information by ID
     * @param pokemonID - The ID pokemon to be search
     * @return Instance of {@link Pokemon}
     */
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Object> getPokemonList(@PathVariable("id") Integer pokemonID){
        return ResponseEntity.ok(pokeService.getPokemonInfo(pokemonID));
    }

    /**
     * Get the pokemon basic information by ID
     * @param evolutionID - The ID pokemon to be search
     * @return Instance of {@link Pokemon}
     */
    @GetMapping("/evolution/{id}")
    public ResponseEntity<List<Evolution>> getEvolution(@PathVariable("id") Integer evolutionID){
        return ResponseEntity.ok(pokeService.getEvolutionInfo(evolutionID));
    }

}
