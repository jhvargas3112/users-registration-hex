package com.company.signup.domain.command;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.users.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler implements CommandHandler {

  private final EventSourcingHandler<User> eventSourcingHandler;

  public UserCommandHandler(EventSourcingHandler<User> eventSourcingHandler) {
    this.eventSourcingHandler = eventSourcingHandler;
  }

  @Override
  public void handle(AddUserCommand addUserCommand) {
    var user = new User(addUserCommand);
    eventSourcingHandler.save(user);
  }

  @Override
  public void handle(UpdateUserCommand updateUserCommand) {
    var user = eventSourcingHandler.getById(updateUserCommand.getId());
    user.setUserName(updateUserCommand.getUserName());
    user.setPassword(updateUserCommand.getPassword());
    user.setBodyMeasurements(
        new BodyMeasurements(updateUserCommand.getHeight(), updateUserCommand.getWeight()));
    user.setBirthDate(new BirthDate(updateUserCommand.getBirthDate()));
    eventSourcingHandler.save(user);
  }

}
