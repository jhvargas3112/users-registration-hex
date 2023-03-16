package com.company.signup.domain.model.statistic;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class BirthYearAvgBmi {

  Integer year;

  Double avgBmi;

  public static BirthYearAvgBmi create(final Integer year, final Double avgBmi) {
    return new BirthYearAvgBmi(year, avgBmi);
  }

}
