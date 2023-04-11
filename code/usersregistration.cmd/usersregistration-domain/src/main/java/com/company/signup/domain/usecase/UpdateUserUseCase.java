package com.company.signup.domain.usecase;

import com.company.signup.domain.command.UpdateUserCommand;

@FunctionalInterface
public interface UpdateUserUseCase {

  void execute(UpdateUserCommand updateUserCommand);

}
