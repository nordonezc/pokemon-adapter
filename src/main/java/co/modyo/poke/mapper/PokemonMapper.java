package co.modyo.poke.mapper;

import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.dto.pokeapi.BasicInfo;
import co.modyo.poke.dto.pokeapi.SpeciesInfo;

/**
 * Use to transform all the information of the pokemon given by the api
 * into a single object with the fields simplified
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class PokemonMapper {

    /**
     * Unify all the data given from the pokemon API into a single object with the info to be used in the consumer
     *
     * @param basicInfo       - The {@link BasicInfo} of the pokemon
     * @param speciesInfo     - The {@link SpeciesInfo} of the pokemon
     * @return Instance of {@link Pokemon}
     */
    public static Pokemon map(BasicInfo basicInfo, SpeciesInfo speciesInfo) {

        return Pokemon.builder()
                .description(DescriptionMapper.map(speciesInfo.getFlavorTextEntries(), "en"))
                .evolutionChain(EvolutionChainMapper.map(speciesInfo.getEvolutionChain()))
                .abilities(AbilityMapper.mapList(basicInfo.getAbilities()))
                .stats(StatMapper.mapList(basicInfo.getStats()))
                .types(TypeMapper.mapList(basicInfo.getTypes()))
                .image(basicInfo.getSprites().getFrontDefault())
                .baseExperience(basicInfo.getBaseExperience())
                .height(basicInfo.getHeight())
                .weight(basicInfo.getWeight())
                .name(basicInfo.getName())
                .build();
    }

    /**
     * Get the id of the pokemon from the given URL
     *
     * @param givenURL - URL with the location of the resource in the pokemon API
     *                 e.g. https://pokeapi.co/api/v2/pokemon-species/1/
     * @return The pokemon ID
     */
    protected static Integer getIDFromURL(String givenURL) {

        String urlWithoutLastBackSlash = givenURL.substring(0, givenURL.length() - 1);

        return Integer.valueOf(
                urlWithoutLastBackSlash.substring(
                        urlWithoutLastBackSlash.lastIndexOf('/' ) + 1)
        );
    }
}
