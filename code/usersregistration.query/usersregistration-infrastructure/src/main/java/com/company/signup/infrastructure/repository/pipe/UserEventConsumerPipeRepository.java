package com.company.signup.infrastructure.repository.pipe;

import com.users.cqrs.core.consumers.EventConsumer;
import com.users.cqrs.core.handlers.EventDestinationHandler;
import com.users.resgistration.common.events.UserAddedEvent;
import com.users.resgistration.common.events.UserUpdatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumerPipeRepository implements EventConsumer {

  private final EventDestinationHandler eventDestinationHandler;

  public UserEventConsumerPipeRepository(EventDestinationHandler eventDestinationHandler) {
    this.eventDestinationHandler = eventDestinationHandler;
  }

  @KafkaListener(topics = "UserAddedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(UserAddedEvent userAddedEvent, Acknowledgment ack) {
    eventDestinationHandler.on(userAddedEvent);
    ack.acknowledge();
  }

  @KafkaListener(topics = "UserUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(UserUpdatedEvent userUpdatedEvent, Acknowledgment ack) {
    eventDestinationHandler.on(userUpdatedEvent);
    ack.acknowledge();
  }

}
