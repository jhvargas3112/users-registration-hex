package com.users.cqrs.core.handlers;

import com.users.resgistration.common.events.UserAddedEvent;
import com.users.resgistration.common.events.UserUpdatedEvent;

public interface EventDestinationHandler {

  void on(UserAddedEvent userAddedEvent);

  void on(UserUpdatedEvent userUpdatedEvent);

}
