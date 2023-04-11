package com.company.signup.domain.repository.event;

import com.users.resgistration.common.events.BaseEvent;
import java.util.List;

@FunctionalInterface
public interface GetEventsRepository {

  List<BaseEvent> execute(String aggregateId);

}
