package com.users.cqrs.core.infrastructure;

import com.users.cqrs.core.commands.BaseCommand;
import com.users.cqrs.core.commands.CommandHandlerMethod;

// Interfaz para implementar el mediador
public interface CommandDispatcher {

  <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

  void send(BaseCommand command);

}
