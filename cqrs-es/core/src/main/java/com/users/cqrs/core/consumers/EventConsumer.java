package com.users.cqrs.core.consumers;

import com.users.resgistration.common.events.UserAddedEvent;
import com.users.resgistration.common.events.UserUpdatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

  void consume(@Payload UserAddedEvent userAddedEvent, Acknowledgment ack);

  void consume(@Payload UserUpdatedEvent userUpdatedEvent, Acknowledgment ack);

}
