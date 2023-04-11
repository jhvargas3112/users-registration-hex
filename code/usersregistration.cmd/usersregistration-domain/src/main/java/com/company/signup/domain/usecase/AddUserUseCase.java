package com.company.signup.domain.usecase;

import com.company.signup.domain.command.AddUserCommand;

@FunctionalInterface
public interface AddUserUseCase {

  void execute(AddUserCommand addUserCommand);

}
