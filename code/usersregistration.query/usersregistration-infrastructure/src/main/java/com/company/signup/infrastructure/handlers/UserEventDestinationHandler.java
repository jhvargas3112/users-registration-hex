package com.company.signup.infrastructure.handlers;

import com.company.signup.domain.model.user.BirthDate;
import com.company.signup.domain.model.user.BodyMeasurements;
import com.company.signup.domain.model.user.User;
import com.company.signup.domain.repository.user.AddUserRepository;
import com.company.signup.domain.repository.user.UpdateUserRepository;
import com.users.cqrs.core.handlers.EventDestinationHandler;
import com.users.resgistration.common.events.UserAddedEvent;
import com.users.resgistration.common.events.UserUpdatedEvent;
import org.springframework.stereotype.Service;

@Service
public class UserEventDestinationHandler implements EventDestinationHandler {

  private final AddUserRepository addUserRepository;

  private final UpdateUserRepository updateUserRepository;

  public UserEventDestinationHandler(AddUserRepository addUserRepository,
      UpdateUserRepository updateUserRepository) {
    this.addUserRepository = addUserRepository;
    this.updateUserRepository = updateUserRepository;
  }

  @Override
  public void on(UserAddedEvent userAddedEvent) {
    addUserRepository.execute(User.builder()
        .id(userAddedEvent.getId())
        .userName(userAddedEvent.getUserName())
        .password(userAddedEvent.getPassword())
        .birthDate(new BirthDate(userAddedEvent.getBirthDate()))
        .bodyMeasurements(
            new BodyMeasurements(userAddedEvent.getHeight(), userAddedEvent.getWeight()))
        .build());
  }

  @Override
  public void on(UserUpdatedEvent userUpdatedEvent) {
    updateUserRepository.execute(User.builder()
        .id(userUpdatedEvent.getId())
        .userName(userUpdatedEvent.getUserName())
        .password(userUpdatedEvent.getPassword())
        .birthDate(new BirthDate(userUpdatedEvent.getBirthDate()))
        .bodyMeasurements(
            new BodyMeasurements(userUpdatedEvent.getHeight(), userUpdatedEvent.getWeight()))
        .build());
  }

}
