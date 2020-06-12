package co.modyo.poke.mapper;

import co.modyo.poke.dto.Stat;
import co.modyo.poke.adapter.dto.info.Stats;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Use to transform the stat given by the pokemon api to the stat that wil be returned
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class StatMapper {

    /**
     * Transform the {@link List} of {@link Stats} into a {@link List}
     * of {@link co.modyo.poke.dto.Stat} with the name of each {@link co.modyo.poke.adapter.dto.info.Stat}
     * with its respective base value
     *
     * @param abilitiesList The {@link List} of {@link Stats} given by the pokemon API
     * @return The {@link List} of {@link co.modyo.poke.dto.Stat}
     */
    protected static List<Stat> mapList(List<Stats> abilitiesList) {

        return abilitiesList.stream()
                .map(StatMapper::map)
                .collect(toList());
    }

    /**
     * Given {@link Stats} get its name with its base value
     *
     * @param stat The {@link Stats} given by the pokemon API
     * @return The name of the {@link co.modyo.poke.dto.Stat}
     */
    protected static Stat map(Stats stat) {

        return Stat.builder()
                .name(stat.getStat().getName())
                .baseStat(stat.getBaseStat())
                .build();
    }
}
