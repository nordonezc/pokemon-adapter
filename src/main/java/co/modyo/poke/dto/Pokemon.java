package co.modyo.poke.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Represent the pokemon information to return to the front
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
@Builder
public class Pokemon {

    /**
     * The {@link List} of abilities that the a pokemon can do
     */
    private List<String> abilities;

    /**
     * The initial value of the experience
     */
    private Integer baseExperience;

    /**
     * The default height
     */
    private Integer height;

    /**
     * The name
     */
    private String name;

    /**
     * The {@link List} with the type of the pokemon
     */
    private List<String> types;

    /**
     * The default weight
     */
    private Integer weight;

    /**
     * The {@link List} of {@link Stat} with his respective value
     */
    private List<Stat> stats;

    /**
     * The ID of evolution chain for the pokemon
     */
    private LinkedID evolutionID;

    /**
     * The basic description of the pokemon
     */
    private String description;

    /**
     * The location of the image of the pokemon
     */
    private String image;

}
