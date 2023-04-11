package com.users.resgistration.common.events;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * This event is raised after the AddUserCommand is executed
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserAddedEvent extends BaseEvent {

  private String userName;

  private String password;

  private LocalDate birthDate;

  private Double height;

  private Double weight;

}
