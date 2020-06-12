package co.modyo.poke.mapper;

import co.modyo.poke.adapter.dto.description.FlavorTextEntries;

import java.util.List;

/**
 * Use to transform the description given by the pokemon api to description in one field
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class DescriptionMapper {

    /**
     * Default description in case no match was found
     */
    private final static String NO_DESCRIPTION = "No description found";

    /**
     * Find the first element of the {@link List} of {@link FlavorTextEntries} which
     * has the {@link co.modyo.poke.adapter.dto.description.Language} equal to language given in the parameter
     *
     * @param flavorTextEntries - The {@link List} of {@link FlavorTextEntries} given by the pokemon API
     * @param language          - The {@link co.modyo.poke.adapter.dto.description.Language} that is wanted to be return
     * @return The description of the pokemon
     */
    protected static String map(List<FlavorTextEntries> flavorTextEntries, String language) {

        return flavorTextEntries.stream()
                .filter(eachTextEntry -> eachTextEntry.getLanguage().getName().equals(language))
                .findFirst()
                .orElse(new FlavorTextEntries().withFlavorText(NO_DESCRIPTION))
                .getFlavorText()
                .replace("\n", "");
    }

}
