package co.modyo.poke.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.services.EvolveService;
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
 * Expose the operations that will be consumed by the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.1.0
 */
@RestController
@RequestMapping("evolution")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EvolutionController {

  /**
   * Service to handle the consult of pokemon
   *
   * @see EvolveService
   */
  private final EvolveService evolveService;

  private final Logger logger = LoggerFactory.getLogger(
    EvolutionController.class
  );

  /**
   * Get the pokemon basic information by ID
   *
   * @param evolutionID - The ID pokemon to be search
   * @return Instance of {@link List} of {@link Evolution}
   */
  @GetMapping("{id}")
  public ResponseEntity<List<Evolution>> getEvolution(
    @PathVariable("id") Integer evolutionID
  ) {
    logger.info("Init get evolution number: {}", evolutionID);
    return ResponseEntity.ok(evolveService.getEvolutionInfo(evolutionID));
  }

  /**
   * Response given to consult the root
   *
   * @return welcome message
   */
  @GetMapping
  public List<Link> home() throws NoSuchMethodException {
    List<Link> availableOperations = new ArrayList<>();
    String firstElement = "first";
    availableOperations.add(
      linkTo(methodOn(EvolutionController.class).getEvolution(null))
        .withRel(firstElement)
    );
    return availableOperations;
  }
}
