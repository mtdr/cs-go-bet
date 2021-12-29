package com.edu.cs.go.bet.auth.repository;

import com.edu.cs.go.bet.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    //select user join openid with some openid
    @Query("select distinct users from User users left join fetch users.userOpenIdsSet o " +
            "where o.openidUrl = :openid")
    User findByOpenid(@Param("openid") String openid);
}
