package co.modyo.poke.mapper;

import co.modyo.poke.controller.PokeApiController;
import co.modyo.poke.dto.LinkedID;
import co.modyo.poke.dto.Pokemon;
import co.modyo.poke.dto.pokeapi.BasicInfo;
import co.modyo.poke.dto.pokeapi.SpeciesInfo;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
     * @param basicInfo   - The {@link BasicInfo} of the pokemon
     * @param speciesInfo - The {@link SpeciesInfo} of the pokemon
     * @return Instance of {@link Pokemon}
     */
    public static Pokemon map(BasicInfo basicInfo, SpeciesInfo speciesInfo) {

        return Pokemon.builder()
                .description(DescriptionMapper.map(speciesInfo.getFlavorTextEntries(), "en"))
                .evolutionID(EvolutionChainMapper.map(speciesInfo.getEvolutionChain()))
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
     * Get the {@link LinkedID} of the pokemon from the given URL
     *
     * @param givenURL URL with the location of the resource in the pokemon API
     *                 <b>e.g.</b> <p color="blue">https://pokeapi.co/api/v2/pokemon-species/1/</p>
     * @param isPokemonRelation based on {@code createLinkedID}
     * @return The pokemon {@link LinkedID}
     * @see PokemonMapper#createLinkedID(Integer, boolean)
     * @see PokemonMapper#getIDFromURL(String)
     */
    protected static LinkedID getLinkedIDFromURL(String givenURL, boolean isPokemonRelation) {

        Integer idEvolution = getIDFromURL(givenURL);
        return createLinkedID(idEvolution, isPokemonRelation);
    }

    // -----------------------------------------------------------
    // INNER LOGIC
    // -----------------------------------------------------------

    /**
     * Get the id of the pokemon from the given URL
     *
     * @param givenURL - URL with the location of the resource in the pokemon API
     *                 e.g. https://pokeapi.co/api/v2/pokemon-species/1/
     * @return The pokemon ID
     */
    private static Integer getIDFromURL(String givenURL) {

        String urlWithoutLastBackSlash = givenURL.substring(0, givenURL.length() - 1);

        return Integer.valueOf(
                urlWithoutLastBackSlash.substring(
                        urlWithoutLastBackSlash.lastIndexOf('/') + 1)
        );
    }

    /**
     * Transform the {@link Integer} given from the URL of the pokemon api
     * into a {@link LinkedID}
     *
     * @param id       - The id given by the URL of the pokemon API
     * @param isPokemonRelation The isPokemonRelation that that will be created:
     *                 <ul>
     *                     <li>true: pokemon</li>
     *                     <li>false: evolution</li>
     *                 </ul>
     * @return The number of the evolution chain
     */
    private static LinkedID createLinkedID(Integer id, boolean isPokemonRelation) {

        LinkedID linkedID = new LinkedID(id);
        Link evolutionLink = isPokemonRelation ?
                linkTo(methodOn(PokeApiController.class).getPokemonList(id)).withRel("pokemon") :
                linkTo(methodOn(PokeApiController.class).getEvolution(id)).withRel("evolution");

        linkedID.add(evolutionLink);

        return linkedID;
    }
}
