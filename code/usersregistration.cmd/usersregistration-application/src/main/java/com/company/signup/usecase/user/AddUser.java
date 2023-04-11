package com.company.signup.usecase.user;

import com.company.signup.domain.command.AddUserCommand;
import com.company.signup.domain.usecase.AddUserUseCase;
import com.users.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

@Service
public class AddUser implements AddUserUseCase {

  private final CommandDispatcher commandDispatcher;

  public AddUser(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @Override
  public void execute(AddUserCommand addUserCommand) {
    commandDispatcher.send(addUserCommand);
  }

}
