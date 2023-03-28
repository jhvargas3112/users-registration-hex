package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearUsersTotal;

@FunctionalInterface
public interface UpdateBirthYearUsersTotalRepository {

  BirthYearUsersTotal execute(BirthYearUsersTotal birthYearUsersTotal);

}
