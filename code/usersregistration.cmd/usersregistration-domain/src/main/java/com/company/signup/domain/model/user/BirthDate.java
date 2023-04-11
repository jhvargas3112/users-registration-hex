package com.company.signup.domain.model.user;

import com.users.resgistration.common.validators.LegalAge;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Value;

@Value
public class BirthDate {

  @NotNull
  @Past
  @LegalAge(value = 18)
  LocalDate date;

  public static BirthDate create(LocalDate date) {
    return new BirthDate(date);
  }

  public static BirthDate create(final Integer year, final Integer month,
      final Integer dayOfMonth) {
    return new BirthDate(LocalDate.of(year, month, dayOfMonth));
  }

  public Integer getYear() {
    return date.getYear();
  }

}
