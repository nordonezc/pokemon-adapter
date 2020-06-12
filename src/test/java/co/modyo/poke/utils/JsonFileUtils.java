package co.modyo.poke.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;

import java.io.File;
import java.io.IOException;

/**
 * Use create objects from json
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
public class JsonFileUtils {

    /**
     * From a path file which contains a valid json format, it creates the requested object
     *
     * @param pathFile      - Location of the file in the resource folder
     * @param expectedClass - Given class to match with the content of the file
     * @param <T>           - Generics to allow any kind of class
     * @return Class object given in the parameters with the content of the file encounter
     */
    public static <T> T readJsonFile(String pathFile, Class<T> expectedClass) {

        return Try.of(() -> {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            File jsonFile = new File(expectedClass.getResource(pathFile).getFile());

            return mapper.readValue(jsonFile, expectedClass);
        }).recover(IOException.class, e -> {

            e.printStackTrace();
            return null;
        }).get();
    }
}
