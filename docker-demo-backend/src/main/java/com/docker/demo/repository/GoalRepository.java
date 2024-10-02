package com.docker.demo.repository;

import com.docker.demo.domain.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends MongoRepository<Goal,String> {

}
