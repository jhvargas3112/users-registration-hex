package com.company.signup.domain.model.event;

import com.users.resgistration.common.events.BaseEvent;
import java.util.Date;

public class Event {

  String id;

  Date timeStamp;

  String aggregateIdentifier;

  String aggregateType;

  Integer version;

  String eventType;

  BaseEvent eventData;

}
