package com.company.signup.domain.repository.user;

import com.company.signup.domain.enums.EventType;

@FunctionalInterface
public interface NotifyNewEventRepository {

  void execute(String eventId, EventType eventType, String associatedValue, String partNumber);
  
}
