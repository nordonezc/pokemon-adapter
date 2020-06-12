package co.modyo.poke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Represent the attributes of the pokemon
 *
 * @author Nicolas Ordoñez Chala
 * @since 1.0.0
 */
@SpringBootApplication
public class PokeApiAdapter {

    public static void main(String[] args) {
        SpringApplication.run(PokeApiAdapter.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
