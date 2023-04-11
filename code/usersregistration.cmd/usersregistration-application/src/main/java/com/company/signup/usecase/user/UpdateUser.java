package com.company.signup.usecase.user;

import com.company.signup.domain.command.UpdateUserCommand;
import com.company.signup.domain.usecase.UpdateUserUseCase;
import com.users.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser implements UpdateUserUseCase {

  private final CommandDispatcher commandDispatcher;

  public UpdateUser(CommandDispatcher commandDispatcher) {
    this.commandDispatcher = commandDispatcher;
  }

  @Override
  public void execute(UpdateUserCommand updateUserCommand) {
    commandDispatcher.send(updateUserCommand);
  }

}
