package com.company.signup.domain.model.user;

import com.company.signup.domain.command.AddUserCommand;
import com.users.cqrs.core.domain.AggregateRoot;
import com.users.resgistration.common.events.UserAddedEvent;
import java.time.LocalDate;
import java.time.Period;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends AggregateRoot {

  @NotBlank
  @Size(min = 8)
  String userName;

  @NotBlank
  String password;

  BirthDate birthDate;

  BodyMeasurements bodyMeasurements;

  public static User create(final String userName, final String password, final BirthDate birthDate,
      final BodyMeasurements bodyMeasurements) {
    return new User(userName, password, birthDate, bodyMeasurements);
  }

  public User(AddUserCommand addUserCommand) {
    raiseEvent(UserAddedEvent.builder()
        .id(addUserCommand.getId())
        .userName(addUserCommand.getUserName())
        .password(addUserCommand.getPassword())
        .birthDate(addUserCommand.getBirthDate())
        .height(addUserCommand.getHeight())
        .weight(addUserCommand.getWeight())
        .build());
  }

  public void apply(UserAddedEvent userAddedEvent) {
    id = userAddedEvent.getId();
    userName = userAddedEvent.getUserName();
    password = userAddedEvent.getPassword();
    birthDate = BirthDate.create(userAddedEvent.getBirthDate());
    bodyMeasurements = BodyMeasurements.create(userAddedEvent.getHeight(),
        userAddedEvent.getWeight());
  }

  public Double calculateBmi() {
    return bodyMeasurements.getWeight() / Math.pow(bodyMeasurements.getHeight(), 2.0);
  }

  public Double getHeight() {
    return bodyMeasurements.getHeight();
  }

  public Double getWeight() {
    return bodyMeasurements.getWeight();
  }

  public Integer getAge() {
    return Period.between(birthDate.getDate(), LocalDate.now()).getYears();
  }

}
