package co.modyo.poke.mapper;

import co.modyo.poke.dto.pokeapi.info.Type;
import co.modyo.poke.dto.pokeapi.info.Types;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use to transform the type given by the pokemon api to the types names that wil be returned
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class TypeMapper {

    /**
     * Transform the {@link List} of {@link Type} into a {@link List}
     * of {@link String} with the name of each {@link Type}
     *
     * @param typeList - The {@link List} of {@link Type} given by the pokemon API
     * @return The {@link List} of types name
     */
    protected static List<String> mapList(List<Types> typeList) {

        return typeList.stream()
                .map(TypeMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Given {@link Types} return the name of type
     *
     * @param type - The {@link Types} given by the pokemon API
     * @return The name of the {@link Type}
     */
    protected static String map(Types type) {

        return type.getType().getName();
    }
}
