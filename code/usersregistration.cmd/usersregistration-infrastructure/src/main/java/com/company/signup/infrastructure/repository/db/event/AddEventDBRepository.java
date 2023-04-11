package com.company.signup.infrastructure.repository.db.event;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.event.AddEventRepository;
import com.company.signup.infrastructure.client.mongo.EventMongoRepository;
import com.users.cqrs.core.infrastructure.repository.entity.Event;
import com.users.cqrs.core.producers.EventProducer;
import com.users.resgistration.common.events.BaseEvent;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AddEventDBRepository implements AddEventRepository {

  private final EventMongoRepository eventMongoRepository;

  private final EventProducer eventProducer;

  public AddEventDBRepository(EventMongoRepository eventMongoRepository,
      EventProducer eventProducer) {
    this.eventMongoRepository = eventMongoRepository;
    this.eventProducer = eventProducer;
  }

  @Override
  public void execute(String aggregateId, Iterable<BaseEvent> events, Integer expectedVersion) {
    var eventStream = eventMongoRepository.findByAggregateIdentifier(
        aggregateId); // Busca eventos ya almacenados, pasándole como parámetro el aggregateId

    var version = !eventStream.isEmpty() ? eventStream.get(eventStream.size() - 1).getVersion()
        : expectedVersion;

    for (var event : events) {
      version++;
      event.setVersion(version);
      var savedEvent = Event.builder()
          .timeStamp(new Date())
          .aggregateIdentifier(aggregateId)
          .aggregateType(User.class.getTypeName())
          .version(version)
          .eventType(event.getClass().getTypeName())
          .eventData(event)
          .build();

      Optional.of(eventMongoRepository.save(savedEvent).getId()).filter(x -> !x.isEmpty())
          .ifPresent(x -> eventProducer.produce(event.getClass().getSimpleName(), event));
    }
  }

}
