package com.users.cqrs.core.infrastructure.repository.entity;

import com.users.resgistration.common.events.BaseEvent;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "eventStore")
public class Event {

  @Id
  private String id;

  private Date timeStamp;

  private String aggregateIdentifier; // Nos permite obtener la lista de eventos que se deben entregar a un determinado aggregate. Principalmente se usa cuando necesitemos re-ejecutar el Event Store

  private String aggregateType;

  private Integer version;

  private String eventType;

  private BaseEvent eventData; // Este es un atributo sumamente importante porque encapsula la descripción de la operación que queremos realizar, por ejemplo, add user

}
