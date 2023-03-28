package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;
import java.util.Optional;

@FunctionalInterface
public interface GetBirthYearUsersTotalByYearRepository {

  Optional<BirthYearUsersTotal> execute(Integer year);
  
}
