package co.modyo.poke.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtils {

    /**
     * Set the basic {@link HttpHeaders} with
     * <ul>
     *     <li>{@link HttpHeaders#USER_AGENT} with the name of the application</li>
     *     <li>{@link HttpHeaders#ACCEPT} with {@link MediaType#APPLICATION_JSON}</li>
     *     <li>{@link HttpHeaders#CONTENT_TYPE} with {@link MediaType#APPLICATION_JSON}</li>
     * </ul>
     *
     * @param input The object to be embedded in the {@link HttpEntity} in case is needed
     * @param <T>   Use of generics to accept any kind of Object
     * @return A {@link HttpEntity} with the needed headers
     */
    public static <T> HttpEntity setInputEntity(T input) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        httpHeaders.add(HttpHeaders.USER_AGENT, "pokedex");
        return new HttpEntity(input, httpHeaders);
    }
}
