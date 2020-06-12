package co.modyo.poke.dto.pokeapi.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represent the attributes of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
public class Sprites {

    /**
     * The image with the pokemon front
     */
    @JsonProperty("front_default")
    private String frontDefault;
}
