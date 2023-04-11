package com.company.signup.infrastructure;

import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.event.AddEventRepository;
import com.company.signup.domain.repository.event.GetEventsRepository;
import com.users.cqrs.core.domain.AggregateRoot;
import com.users.cqrs.core.handlers.EventSourcingHandler;
import com.users.resgistration.common.events.BaseEvent;
import java.util.Comparator;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserEventSourcingHandler implements EventSourcingHandler<User> {

  private final AddEventRepository addEventRepository;

  private final GetEventsRepository getEventsRepository;

  public UserEventSourcingHandler(AddEventRepository addEventRepository,
      GetEventsRepository getEventsRepository) {
    this.addEventRepository = addEventRepository;
    this.getEventsRepository = getEventsRepository;
  }

  @Override
  public void save(AggregateRoot aggregate) {
    addEventRepository.execute(aggregate.getId(), aggregate.getUncommitedChanges(),
        aggregate.getVersion());

    aggregate.markChangesAsCommited();
  }

  @Override
  public User getById(String id) {
    var user = new User();
    var events = getEventsRepository.execute(id);

    Optional.ofNullable(events).filter(x -> !x.isEmpty()).ifPresent(x -> {
      user.replayEvents(events);
      events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder())
          .ifPresent(user::setVersion);
    });

    return user;
  }

}
