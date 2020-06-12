package co.modyo.poke.adapter.dto.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represent the pair of description with its language of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlavorTextEntries {

    /**
     * Description of the pokemon
     */
    @With
    @JsonProperty("flavor_text")
    private String flavorText;

    /**
     * The {@link Language} of the returned flavor text
     */
    private Language language;
}
