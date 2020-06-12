package co.modyo.poke.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * The ID of evolution chain for the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public class LinkedID extends RepresentationModel<LinkedID> {

    /**
     * The ID of evolution chain for the pokemon
     */
    private final Integer id;


}
