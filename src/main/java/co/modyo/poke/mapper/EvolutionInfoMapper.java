package co.modyo.poke.mapper;

import co.modyo.poke.dto.Evolution;
import co.modyo.poke.dto.EvolutionInfo;
import co.modyo.poke.adapter.dto.EvolutionDetail;
import co.modyo.poke.adapter.dto.evolution.Chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.modyo.poke.mapper.PokemonMapper.*;
import static co.modyo.poke.utils.HttpUtils.toSentenceCase;

/**
 * Use to transform the type given chain evolution to the name of the
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class EvolutionInfoMapper {

    /**
     * Transform the {@link EvolutionDetail} given by the API into
     * a {@link List} of {@link EvolutionInfo}
     *
     * @param evolutionDetail - The {@link EvolutionDetail} given by the pokemon API+
     * @return The {@link List} of {@link Evolution}
     */
    public static List<Evolution> map(EvolutionDetail evolutionDetail) {

        List<Evolution> evolutionsWays = new ArrayList<>();
        EvolutionInfo initialPokemon = getEvolutionInfo(evolutionDetail.getChain());

        List<Chain> possibleEvolutions = evolutionDetail.getChain().getEvolvesTo();
        possibleEvolutions.forEach(chain -> {

            List<EvolutionInfo> evolutionInfos = new ArrayList<>();
            evolutionInfos.add(initialPokemon);

            Chain nextEvolve = chain;
            while (nextEvolve != null) {

                evolutionInfos.add(getEvolutionInfo(nextEvolve));
                nextEvolve = nextEvolve(nextEvolve);
            }

            evolutionsWays.add(new Evolution(evolutionInfos));
        });

        return evolutionsWays;
    }

    /**
     * Get the info of the pokemon which {@link Chain}
     *
     * @param evolvesTo - Instance of {@link Chain}
     * @return The {@link EvolutionInfo}
     */
    private static EvolutionInfo getEvolutionInfo(Chain evolvesTo) {

        return EvolutionInfo.builder()
                .name(toSentenceCase(evolvesTo.getSpecies().getName()))
                .pokemonID(
                        getLinkedIDFromURL(evolvesTo.getSpecies().getUrl(), true))
                .build();
    }

    /**
     * Check if the current evolve has more evolutions associated
     *
     * @param currentEvolve - Instance of {@link Chain}
     * @return The next evolve, or null
     */
    private static Chain nextEvolve(Chain currentEvolve) {

        List<Chain> nextEvolve = Optional.of(currentEvolve.getEvolvesTo()).orElse(new ArrayList<>());

        return !currentEvolve.getEvolvesTo().isEmpty() ?
                nextEvolve.get(0) :
                null;
    }
}
