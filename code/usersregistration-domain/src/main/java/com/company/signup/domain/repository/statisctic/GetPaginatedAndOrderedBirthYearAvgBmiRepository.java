package com.company.signup.domain.repository.statisctic;

import com.company.signup.domain.model.statistic.BirthYearAvgBmi;
import com.company.signup.domain.ResultsPage;

@FunctionalInterface
public interface GetPaginatedAndOrderedBirthYearAvgBmiRepository {

  ResultsPage<BirthYearAvgBmi> execute(Integer pageNumber, Integer sizePage, String sort);

}
