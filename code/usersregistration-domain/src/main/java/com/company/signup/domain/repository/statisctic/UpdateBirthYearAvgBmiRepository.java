package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;

@FunctionalInterface
public interface UpdateBirthYearAvgBmiRepository {

  BirthYearAvgBmi execute(BirthYearAvgBmi birthYearAvgBmi);

}
