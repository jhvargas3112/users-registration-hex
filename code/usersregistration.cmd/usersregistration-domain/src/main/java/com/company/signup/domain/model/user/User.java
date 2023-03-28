package com.company.signup.domain.model.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class User {

  Long id;

  @NotBlank
  @Size(min = 8)
  String userName;

  @NotBlank
  String password;

  BirthDate birthDate;

  BodyMeasurements bodyMeasurements;

  public static User create(final Long id, final String userName, final String password,
      final BirthDate birthDate, final BodyMeasurements bodyMeasurements) {
    return new User(id, userName, password, birthDate, bodyMeasurements);
  }

  public Double calculateBmiValue() {
    return this.bodyMeasurements.calculateBmi();
  }

  public Double getHeight() {
    return this.getBodyMeasurements().getHeight();
  }

  public Double getWeight() {
    return this.getBodyMeasurements().getWeight();
  }

}
