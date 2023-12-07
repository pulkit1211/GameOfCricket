package com.demo.gameofcricket.repository;

import com.demo.gameofcricket.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player,String> {
    Player findByName(String name);
}
