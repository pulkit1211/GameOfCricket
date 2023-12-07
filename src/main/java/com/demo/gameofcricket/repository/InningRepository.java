package com.demo.gameofcricket.repository;

import com.demo.gameofcricket.entity.Inning;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InningRepository extends MongoRepository<Inning,String> {
    Inning findByTeamName(String teamName);
}
