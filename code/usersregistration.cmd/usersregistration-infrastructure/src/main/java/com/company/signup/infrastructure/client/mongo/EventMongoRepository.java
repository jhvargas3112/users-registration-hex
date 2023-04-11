package com.company.signup.infrastructure.client.mongo;

import com.users.cqrs.core.infrastructure.repository.entity.Event;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventMongoRepository extends MongoRepository<Event, String> {

  List<Event> findByAggregateIdentifier(String aggregateIdentifier);

}
