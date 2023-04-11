package com.company.signup.infrastructure.repository.pipe;

import com.users.cqrs.core.producers.EventProducer;
import com.users.resgistration.common.events.BaseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventProducerPipeRepository implements EventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public UserEventProducerPipeRepository(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void produce(String topic, BaseEvent event) {
    kafkaTemplate.send(topic, event);
  }

}
