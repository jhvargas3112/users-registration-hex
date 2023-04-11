package com.users.cqrs.core.producers;

import com.users.resgistration.common.events.BaseEvent;

public interface EventProducer {

  void produce(String topic, BaseEvent event);

}
