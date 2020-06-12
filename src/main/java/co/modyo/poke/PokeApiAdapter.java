package co.modyo.poke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PokeApiAdapter {

    public static void main(String[] args) {
        SpringApplication.run(PokeApiAdapter.class, args);
    }
}
