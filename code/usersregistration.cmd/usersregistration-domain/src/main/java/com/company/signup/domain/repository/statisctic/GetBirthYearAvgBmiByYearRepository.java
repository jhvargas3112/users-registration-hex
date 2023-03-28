package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import java.util.Optional;

@FunctionalInterface
public interface GetBirthYearAvgBmiByYearRepository {

  Optional<BirthYearAvgBmi> execute(Integer year);

}
