package com.company.signup.domain.model.user;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {

  Long id;

  String userName;

  String password;

  LocalDate birthDate;

  Float height;

  Float weight;

  public static User create(final Long id, final String userName, final String password,
      final LocalDate birthDate, final Float height, final Float weight) {
    return new User(id, userName, password, birthDate, height, weight);
  }

  public Double calculateBmiValue() {
    return weight / Math.pow(height, 2.0);
  }

}
