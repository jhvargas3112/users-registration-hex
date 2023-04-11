package com.company.signup.domain.command;

public interface CommandHandler {

  void handle(AddUserCommand addUserCommand);

  void handle(UpdateUserCommand updateUserCommand);

}
