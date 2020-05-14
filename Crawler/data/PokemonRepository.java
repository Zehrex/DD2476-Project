22
https://raw.githubusercontent.com/anabneri/Pokedex/master/src/main/java/com/pokedex/reactiveweb/repository/PokemonRepository.java
package com.pokedex.reactiveweb.repository;

import com.pokedex.reactiveweb.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository <Pokemon, String> {
}
