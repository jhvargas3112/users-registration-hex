package com.company.signup.infrastructure;

import com.users.cqrs.core.commands.BaseCommand;
import com.users.cqrs.core.commands.CommandHandlerMethod;
import com.users.cqrs.core.infrastructure.CommandDispatcher;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
// Lo veo más como un component, al contrario de lo que dice el tutorial, que lo ve como un @Service
public class UserCommandDispatcher implements CommandDispatcher {

  private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

  @Override
  public <T extends BaseCommand> void registerHandler(Class<T> type,
      CommandHandlerMethod<T> handler) {
    var handlers = routes.computeIfAbsent(type, x -> new LinkedList<>());
    handlers.add(handler);
  }

  @Override
  public void send(BaseCommand command) {
    Optional.ofNullable(routes.get(command.getClass())).filter(x -> x.size() == 1)
        .orElseThrow(() -> new RuntimeException("Command execution problem")).get(0)
        .handle(command);
  }

}
