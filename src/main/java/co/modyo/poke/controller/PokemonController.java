package co.modyo.poke.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.services.PokeService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose the pokemon operations that will be consumed by the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@RestController
@RequestMapping("pokemon")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PokemonController {

  /**
   * Service to handle the consult of pokemon
   *
   * @see PokeService
   */
  private final PokeService pokeService;

  private final Logger logger = LoggerFactory.getLogger(
    PokemonController.class
  );

  /**
   * Get amount of pokemon available to consult
   *
   * @return Instance of {@link Pokemon}
   */
  @GetMapping
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
  @GetMapping("/{id}")
  public ResponseEntity<Pokemon> getPokemon(
    @PathVariable("id") Integer pokemonID
  ) {
    logger.info("Init get pokemon number: {}", pokemonID);
    return ResponseEntity.ok(pokeService.getPokemonInfo(pokemonID));
  }

  /**
   * Response given to consult the root
   *
   * @return welcome message
   */
  @GetMapping("/")
  public List<Link> home() throws NoSuchMethodException {
    List<Link> availableOperations = new ArrayList<>();
    String firstElement = "first";
    availableOperations.add(
      linkTo(methodOn(PokemonController.class).getPokemon(null))
        .withRel(firstElement)
    );

    availableOperations.add(
      linkTo(methodOn(PokemonController.class).countPokemon())
        .withRel(firstElement)
    );
    return availableOperations;
  }
}
