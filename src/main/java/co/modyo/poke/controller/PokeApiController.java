package co.modyo.poke.controller;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.services.PokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Expose the operations that will be consumed by the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@RestController
@CrossOrigin(origins = "*")
public class PokeApiController {


    /**
     * Service to handle the consult of pokemon
     *
     * @see PokeService
     */
    private final PokeService pokeService;

    /**
     * Create the home reference for all the methods
     */
    private final List<Link> availableOperations;


    private final Logger logger = LoggerFactory.getLogger(PokeApiController.class);

    /**
     * Constructor which initialize the methods that it has
     *
     * @param pokeService - Required by constructor
     */
    public PokeApiController(PokeService pokeService) throws NoSuchMethodException {
        this.pokeService = pokeService;

        availableOperations = new ArrayList<>();
        Method getEvolution = PokeApiController.class.getMethod("getEvolution", Integer.class);
        availableOperations.add(linkTo(getEvolution, "id").withRel("first"));

        Method getPokemon = PokeApiController.class.getMethod("getPokemon", Integer.class);
        availableOperations.add(linkTo(getPokemon, "id").withRel("first"));

        availableOperations.add(linkTo(methodOn(PokeApiController.class).countPokemon()).withRel("first"));
    }

    /**
     * Get amount of pokemon available to consult
     *
     * @return Instance of {@link Pokemon}
     */
    @GetMapping("/pokemon")
    public ResponseEntity<Integer> countPokemon() {
        logger.info("Init count pokemon");
        return ResponseEntity.ok(pokeService.countPokemon());
    }

    /**
     * Get the pokemon basic information by ID
     *
     * @param pokemonID - The ID pokemon to be search
     * @return Instance of {@link Pokemon}
     */
    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Pokemon> getPokemon(@PathVariable("id") Integer pokemonID) {
        logger.info("Init get pokemon number: {}", pokemonID);
        return ResponseEntity.ok(pokeService.getPokemonInfo(pokemonID));
    }

    /**
     * Get the pokemon basic information by ID
     *
     * @param evolutionID - The ID pokemon to be search
     * @return Instance of {@link List} of {@link Evolution}
     */
    @GetMapping("/evolution/{id}")
    public ResponseEntity<List<Evolution>> getEvolution(@PathVariable("id") Integer evolutionID) {
        logger.info("Init get evolution number: {}", evolutionID);
        return ResponseEntity.ok(pokeService.getEvolutionInfo(evolutionID));
    }

    /**
     * Response given to consult the root
     *
     * @return welcome message
     */
    @GetMapping("/")
    public List<Link> home() throws NoSuchMethodException {

        return availableOperations;
    }


}
