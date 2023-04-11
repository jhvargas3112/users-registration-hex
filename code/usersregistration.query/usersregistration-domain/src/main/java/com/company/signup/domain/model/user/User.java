package com.company.signup.domain.model.user;

import java.time.LocalDate;
import java.time.Period;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

  String id;

  @NotBlank
  @Size(min = 8)
  String userName;

  @NotBlank
  String password;

  BirthDate birthDate;

  BodyMeasurements bodyMeasurements;

  public static User create(final String id, final String userName, final String password,
      final BirthDate birthDate, final BodyMeasurements bodyMeasurements) {
    return new User(id, userName, password, birthDate, bodyMeasurements);
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
