package com.edu.cs.go.bet.match.repository;

import com.edu.cs.go.bet.match.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends MongoRepository<Game, UUID> {
}
