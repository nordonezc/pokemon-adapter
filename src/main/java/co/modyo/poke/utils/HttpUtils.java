package co.modyo.poke.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtils {

  private HttpUtils() {}

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
  public static <T> HttpEntity<T> setInputEntity(T input) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(
      HttpHeaders.CONTENT_TYPE,
      MediaType.APPLICATION_JSON.toString()
    );
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
    httpHeaders.add(HttpHeaders.USER_AGENT, "pokedex");
    return new HttpEntity<>(input, httpHeaders);
  }

  /**
   * Transform the given input into a sentence case. Take the first letter
   * with {@link String#substring(int, int)} with 0 and 1 respectively
   * apply {@link String#toUpperCase()} and finally concatenate it
   * with {@link String#substring(int)} with 1.
   *
   * @param input - Pokemon's name or ability
   * @return Input in sentence case.  e.g. house will return House
   */
  public static String toSentenceCase(String input) {
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }
}
