package co.modyo.poke.mapper;

import co.modyo.poke.adapter.dto.info.Abilities;
import co.modyo.poke.adapter.dto.info.Ability;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Use to transform the type given by the pokemon api to the abilities names that wil be returned
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class AbilityMapper {

    /**
     * Transform the {@link List} of {@link Abilities} into a {@link List}
     * of {@link String} with the name of each {@link Ability}
     *
     * @param abilitiesList - The {@link List} of {@link Abilities} given by the pokemon API
     * @return The {@link List} of abilities name
     */
    protected static List<String> mapList(List<Abilities> abilitiesList) {

        return abilitiesList.stream()
                .map(AbilityMapper::map)
                .collect(toList());
    }

    /**
     * Given {@link Abilities} return the name of the embedded ability
     *
     * @param abilities - The {@link Abilities} given by the pokemon API
     * @return The name of the {@link Abilities}
     */
    protected static String map(Abilities abilities) {

        return abilities.getAbility().getName();
    }
}
