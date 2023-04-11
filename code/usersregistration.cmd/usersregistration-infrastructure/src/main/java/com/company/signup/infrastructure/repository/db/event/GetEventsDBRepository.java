package com.company.signup.infrastructure.repository.db.event;

import com.company.signup.domain.repository.event.GetEventsRepository;
import com.company.signup.infrastructure.client.mongo.EventMongoRepository;
import com.users.cqrs.core.infrastructure.repository.entity.Event;
import com.users.resgistration.common.events.BaseEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GetEventsDBRepository implements GetEventsRepository {

  private final EventMongoRepository eventMongoRepository;

  public GetEventsDBRepository(EventMongoRepository eventMongoRepository) {
    this.eventMongoRepository = eventMongoRepository;
  }

  @Override
  public List<BaseEvent> execute(String aggregateId) {
    var events = eventMongoRepository.findByAggregateIdentifier(aggregateId);

    return Optional.ofNullable(events).filter(x -> !x.isEmpty())
        .orElseThrow(() -> new RuntimeException("Incorrect user id")).stream()
        .map(Event::getEventData).collect(Collectors.toList());
  }

}
