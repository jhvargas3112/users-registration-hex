package com.company.signup.domain.model.statistic;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class BirthYearUsersTotal {

  Integer year;

  Integer total;

  public static BirthYearUsersTotal create(final Integer year, final Integer total) {
    return new BirthYearUsersTotal(year, total);
  }

}
