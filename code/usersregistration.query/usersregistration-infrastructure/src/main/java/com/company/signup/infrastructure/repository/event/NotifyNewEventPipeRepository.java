package com.company.signup.infrastructure.repository.event;

import com.company.signup.domain.enums.EventType;
import com.company.signup.domain.repository.user.NotifyNewEventRepository;
import org.springframework.stereotype.Service;

@Service
public class NotifyNewEventPipeRepository implements NotifyNewEventRepository {

  @Override
  public void execute(String eventId, EventType eventType, String associatedValue,
      String partNumber) {

  }
}
