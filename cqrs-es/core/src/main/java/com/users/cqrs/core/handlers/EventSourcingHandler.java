package com.users.cqrs.core.handlers;

import com.users.cqrs.core.domain.AggregateRoot;

/**
 * Interface genérica que atraviesa al CommandHandler para obtener el último estado de un aggregate.
 * De esta forma se puede obtener aquellos cambios que aún no han sido registrados y que
 * posteriormente se almacenan en la base de datos del Event Store
 * @param <T>
 */
public interface EventSourcingHandler<T> {
  
  void save(AggregateRoot aggregate);
  
  T getById(String id); // id es el aggregateId del aggregate que quiero buscar

}
