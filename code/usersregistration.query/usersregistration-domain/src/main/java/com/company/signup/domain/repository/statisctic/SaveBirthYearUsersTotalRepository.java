package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;

@FunctionalInterface
public interface SaveBirthYearUsersTotalRepository {

  BirthYearUsersTotal execute(BirthYearUsersTotal birthYearUsersTotal);

}
