package com.company.signup.domain.repository.event;

import com.users.resgistration.common.events.BaseEvent;

@FunctionalInterface
public interface AddEventRepository {

  void execute(String aggregateId, Iterable<BaseEvent> events, Integer expectedVersion);

}
