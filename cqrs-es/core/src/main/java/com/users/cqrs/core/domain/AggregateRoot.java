package com.users.cqrs.core.domain;

import com.users.resgistration.common.events.BaseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

// Se encarga de mantener la lista de eventos
@Slf4j
@Data
public abstract class AggregateRoot {

  protected String id;

  private Integer version = -1;

  private final List<BaseEvent> changes = new ArrayList<>(); // cuando esta lista está empty, significa que no hay ningún cambio por confirmar

  private final Logger logger = log;

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public List<BaseEvent> getUncommitedChanges() {
    return this.changes;
  }

  public void markChangesAsCommited() {
    changes.clear();
  }

  protected void applyChange(BaseEvent event, Boolean isNew) {
    try {
      var method = getClass().getDeclaredMethod("apply", event.getClass());
      method.setAccessible(true);
      method.invoke(this, event);
    } catch (NoSuchMethodException ex1) {
      log.warn("The apply method was not been found for class {}", event.getClass().getName());
    } catch (Exception ex2) {
      log.error("Error applying the event to the aggregate", ex2);
    } finally { // Siempre se va a ejecutar
      Optional.ofNullable(isNew).filter(x -> x).ifPresent(x -> changes.add(event));
    }
  }

  public void raiseEvent(
      BaseEvent event) { // Cuando se quiera ejecutar un nuevo event, es decir, cuando se quiera agregar un nuevo evento al aggregate
    applyChange(event, true);
  }

  public void   replayEvents(
      Iterable<BaseEvent> events) { // Cuando se quiera reprocesar todos los eventos que ya existen dentro del aggregate, es decir, si quisiéramos reprocesar a todos los eventos que están dentro de la propiedad colección changes
    events.forEach(e -> applyChange(e, false));
  }

}
